package com.cotrafa.prueba_tecnica.infrastructure.config;

import com.cotrafa.prueba_tecnica.application.LoanService;
import com.cotrafa.prueba_tecnica.application.PaymentPlanService;
import com.cotrafa.prueba_tecnica.application.UserService;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanStateRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.LoanTypeRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.loan.ports.out.NotificationRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.in.IPaymentPlanService;
import com.cotrafa.prueba_tecnica.domain.payment_plan.port.out.PaymentPlanRepositoryPort;
import com.cotrafa.prueba_tecnica.domain.user.ports.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }

    @Bean
    public LoanService loanService(UserRepositoryPort userRepositoryPort, LoanRepositoryPort loanRepositoryPort,
                                   LoanTypeRepositoryPort loanTypeRepositoryPort, LoanStateRepositoryPort loanStateRepositoryPort,
                                   IPaymentPlanService paymentPlanService, NotificationRepositoryPort notificationRepositoryPort){
        return new LoanService(userRepositoryPort, loanRepositoryPort, loanTypeRepositoryPort,
                loanStateRepositoryPort, paymentPlanService, notificationRepositoryPort);
    }

    @Bean
    public PaymentPlanService paymentPlanService(PaymentPlanRepositoryPort paymentPlanRepositoryPort){
        return new PaymentPlanService(paymentPlanRepositoryPort);
    }
}
