package br.com.wallet.conta.publisher;

import br.com.wallet.conta.publisher.event.TransacaoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqPublisher {
    private final RabbitTemplate template;
    private final ApplicationEventPublisher publisher;

    @Async
    public void enviarRabbitMQ(TransacaoEvent mensagem) {
        log.info("Enviando mensagem {}", mensagem);
        this.publisher.publishEvent(mensagem);
        this.template.convertAndSend("mensagem");
    }

    @RabbitListener(queues = "transacao-service-queue")
    public void teste(@Payload TransacaoEvent mensagem) {
        log.info("mensagem {}", mensagem);
    }
}
