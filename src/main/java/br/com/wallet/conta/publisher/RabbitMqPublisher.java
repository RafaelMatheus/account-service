package br.com.wallet.conta.publisher;

import br.com.wallet.conta.publisher.event.TransacaoEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static br.com.wallet.conta.constants.RabbitmqConstants.EXCHANGE_NAME;
import static br.com.wallet.conta.constants.RabbitmqConstants.ROUTING_KEY;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqPublisher {
    private final RabbitTemplate template;
    private final ApplicationEventPublisher publisher;

    @Async
    public void enviarRabbitMQ(TransacaoEvent mensagem) throws JsonProcessingException {
        log.info("Enviando mensagem {}", mensagem);
        this.template.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, new ObjectMapper().writeValueAsString(mensagem));
    }

}
