INSERT INTO loan_states (id, name) VALUES
(1, 'Pendiente de revisión'),
(2, 'Aprobada'),
(3, 'Revisión manual'),
(4, 'Rechazada');

INSERT INTO loan_types (id, name, interest_rate, automatic_validation) VALUES
(1, 'Libre Inversión', 0.13, TRUE),
(2, 'Vehículo', 0.095, TRUE),
(3, 'Vivienda', 0.085, FALSE),
(4, 'Educación', 0.070, TRUE);