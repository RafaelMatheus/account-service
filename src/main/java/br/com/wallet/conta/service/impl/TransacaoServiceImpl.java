package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import br.com.wallet.conta.publisher.RabbitMqPublisher;
import br.com.wallet.conta.publisher.event.*;
import br.com.wallet.conta.service.ContaService;
import br.com.wallet.conta.service.TransacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {
    private final ContaService service;
    private final RabbitMqPublisher publisher;

    @Override
    public void realizarTransacao(TransacaoRequest transacaoRequest) throws JsonProcessingException {
        this.service.buscarContaPorId(transacaoRequest.getIdContaOrigem());

        this.publisher.enviarRabbitMQ(this.transacaoEventFactory(transacaoRequest));
    }

    private TransacaoEvent transacaoEventFactory(TransacaoRequest transacaoRequest) {
        if (transacaoRequest.getTipoTransacao().equals(TipoTransacao.SAQUE)) {
            return Saque.builder()
                    .tipoTransacao(transacaoRequest.getTipoTransacao())
                    .contaOrigem(transacaoRequest.getIdContaDestino())
                    .valorDebitado(transacaoRequest.getValorTransacao())
                    .terminal("TERMINAL PICPAY")
                    .timestamp(OffsetDateTime.now())
                    .build();
        } else if (transacaoRequest.getTipoTransacao().equals(TipoTransacao.TRANSFERENCIA)) {
            return Transferencia.builder()
                    .tipoTransacao(TipoTransacao.TRANSFERENCIA)
                    .valorDebitado(transacaoRequest.getValorTransacao())
                    .contaDestino(transacaoRequest.getIdContaDestino())
                    .contaOrigem(transacaoRequest.getIdContaOrigem())
                    .timestamp(OffsetDateTime.now())
                    .build();
        } else if (transacaoRequest.getTipoTransacao().equals(TipoTransacao.DEPOSITO)) {
            return Deposito.builder()
                    .tipoTransacao(TipoTransacao.DEPOSITO)
                    .valorDebitado(transacaoRequest.getValorTransacao())
                    .contaOrigem(transacaoRequest.getIdContaOrigem())
                    .timestamp(OffsetDateTime.now())
                    .build();

        }
        return Recebimento.builder()
                .valorCreditado(transacaoRequest.getValorTransacao())
                .tipoTransacao(TipoTransacao.RECEBIMENTO)
                .contaOrigem(transacaoRequest.getIdContaOrigem())
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
