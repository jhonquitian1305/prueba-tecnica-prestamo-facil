package com.cotrafa.prueba_tecnica.infrastructure.notification;

import com.cotrafa.prueba_tecnica.domain.loan.ports.out.NotificationRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationAdapter implements NotificationRepositoryPort {

    @Override
    public void sendNotification(String email, boolean approved) {
        log.info("Enviando notificación a {}", email);
        log.info("El préstamo ha sido {}", approved ? "aprobado" : "no aprobado");
    }
}
