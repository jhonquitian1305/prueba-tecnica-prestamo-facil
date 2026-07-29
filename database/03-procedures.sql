-- Se creó esta función para apartar la lógica del cálculo del interés mensual en el stored procedure
CREATE OR REPLACE FUNCTION calculate_monthly_payment(
    p_amount NUMERIC,
    p_annual_interest NUMERIC,
    p_months INTEGER
)
RETURNS NUMERIC
LANGUAGE plpgsql
AS
$$
DECLARE
v_monthly_rate NUMERIC;
    v_factor NUMERIC;
BEGIN
    v_monthly_rate := (p_annual_interest) / 12;

    v_factor := POWER(1 + v_monthly_rate, p_months);

RETURN ROUND(
        (p_amount * (v_monthly_rate * v_factor))
            / (v_factor - 1),
        2
       );

END;
$$;

--------------------------------------------

CREATE OR REPLACE PROCEDURE evaluate_loan(
    IN p_loan_id BIGINT,
    OUT p_state BIGINT,
    OUT p_monthly_payment NUMERIC
)
LANGUAGE plpgsql
AS
$$
DECLARE

-- Estados
    v_state_pending_review CONSTANT BIGINT := 1;
    v_state_approved CONSTANT BIGINT := 2;
    v_state_manual_review CONSTANT BIGINT := 3;
    v_state_rejected CONSTANT BIGINT := 4;

    -- Datos del préstamo
    v_user_id BIGINT;
    v_base_salary NUMERIC;
    v_amount NUMERIC;
    v_term_months INTEGER;
    v_interest_rate NUMERIC;

    -- Cálculos
    v_maximum_capacity NUMERIC;
    v_current_monthly_debt NUMERIC := 0;
    v_available_capacity NUMERIC;

BEGIN

    -----------------------------------------------------------------
    -- Obtener la información del préstamo
    -----------------------------------------------------------------

SELECT
    l.user_id,
    u.base_salary,
    l.amount,
    l.term_months,
    lt.interest_rate
INTO
    v_user_id,
    v_base_salary,
    v_amount,
    v_term_months,
    v_interest_rate
FROM loans l
         INNER JOIN users u
                    ON u.id = l.user_id
         INNER JOIN loan_types lt
                    ON lt.id = l.loan_type_id
WHERE l.id = p_loan_id;

IF NOT FOUND THEN
        RAISE EXCEPTION 'Loan with id % does not exist.', p_loan_id;
END IF;

    -----------------------------------------------------------------
    -- Capacidad máxima
    -----------------------------------------------------------------

    v_maximum_capacity := ROUND(v_base_salary * 0.35, 2);

    -----------------------------------------------------------------
    -- Calcular deuda mensual actual
    -----------------------------------------------------------------

SELECT COALESCE(SUM(pp.monthly_payment), 0)
INTO v_current_monthly_debt
FROM loans l
         INNER JOIN payment_plans pp
                    ON pp.loan_id = l.id
WHERE l.user_id = v_user_id
  AND l.loan_state_id = v_state_approved
  AND pp.fee_number = 1
  AND l.id <> p_loan_id;

-----------------------------------------------------------------
-- Capacidad disponible
-----------------------------------------------------------------

v_available_capacity :=
        v_maximum_capacity - v_current_monthly_debt;

    -----------------------------------------------------------------
    -- Calcular cuota del préstamo evaluado
    -----------------------------------------------------------------

    p_monthly_payment :=
        calculate_monthly_payment(
            v_amount,
            v_interest_rate,
            v_term_months
        );

    -----------------------------------------------------------------
    -- Evaluar solicitud
    -----------------------------------------------------------------

    IF p_monthly_payment <= v_available_capacity THEN

        IF v_amount > (v_base_salary * 5) THEN
            p_state := v_state_manual_review;
ELSE
            p_state := v_state_approved;
END IF;

ELSE

        p_state := v_state_rejected;

END IF;

END;
$$;
