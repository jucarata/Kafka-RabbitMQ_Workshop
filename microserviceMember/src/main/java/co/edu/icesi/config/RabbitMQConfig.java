package co.edu.icesi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nombres para evitar errores de tipeo y facilitar mantenibilidad
    public static final String EXCHANGE_NAME = "notificacion.exchange";
    public static final String NOTIFICATION_QUEUE_NAME = "Notification";
    public static final String DEAD_LETTER_QUEUE_NAME = "DeadLQ";
    public static final String NOTIFICATION_ROUTING_KEY = "notificacion.success";
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME, true);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange notificacionExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue,
                                       TopicExchange notificacionExchange) {
        return BindingBuilder
                .bind(notificationQueue)
                .to(notificacionExchange)
                .with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue,
                                     TopicExchange notificacionExchange) {
        return BindingBuilder
                .bind(deadLetterQueue)
                .to(notificacionExchange)
                .with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}