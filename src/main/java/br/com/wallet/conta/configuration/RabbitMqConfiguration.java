package br.com.wallet.conta.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableRabbit
@EnableAsync
public class RabbitMqConfiguration {
    @Value("${queue.name}")
    private String fila;

    @Bean
    public Queue queue() {
        return new Queue(fila, true);
    }
}
