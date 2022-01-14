package br.com.wallet.conta.publisher;

import br.com.wallet.conta.publisher.event.TransacaoEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
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
        final MessageProperties messageProperties = new MessageProperties();

        this.sendMessage(mensagem, EXCHANGE_NAME, messageProperties, ROUTING_KEY);
    }

    private void sendMessage(final Object message, final String exchange, final MessageProperties messageProperties, final String routingKey) throws JsonProcessingException {
        messageProperties.setContentType(MediaType.APPLICATION_JSON_VALUE);
        template.convertAndSend(exchange, routingKey != null ? routingKey : messageProperties.getReceivedRoutingKey(),
                new Message(new ObjectMapper().writeValueAsBytes(message), messageProperties));

    }

}
