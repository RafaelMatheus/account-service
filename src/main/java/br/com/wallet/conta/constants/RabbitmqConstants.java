package br.com.wallet.conta.constants;

public class RabbitmqConstants {
    private RabbitmqConstants() {
    }

    public static final String EXCHANGE_NAME = "transacao-service-exchange";

    public static final String QUEUE_NAME = "transacao-service-queue";

    public static final String ROUTING_KEY = "transacao.routing.#";
}
