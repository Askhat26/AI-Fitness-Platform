package smartfitness.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_NOTIFICATION_QUEUE = "user.notification.queue";

    @Bean
    public Queue notificationQueue() {
        return new Queue(USER_NOTIFICATION_QUEUE, true);
    }
}