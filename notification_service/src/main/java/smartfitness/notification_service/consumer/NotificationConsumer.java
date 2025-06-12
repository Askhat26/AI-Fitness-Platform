package smartfitness.notification_service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import smartfitness.notification_service.config.RabbitMQConfig;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.USER_NOTIFICATION_QUEUE)
    public void consumeMessage(String message) {
        // Simulate sending email notification
        System.out.println("[NotificationService] New User Registered: " + message);
    }
}