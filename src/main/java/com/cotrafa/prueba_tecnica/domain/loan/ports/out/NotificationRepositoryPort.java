package com.cotrafa.prueba_tecnica.domain.loan.ports.out;

public interface NotificationRepositoryPort {
    void sendNotification(String email, boolean approved);
}
