package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import br.com.wallet.conta.publisher.RabbitMqPublisher;
import br.com.wallet.conta.publisher.event.Deposito;
import br.com.wallet.conta.publisher.event.Saque;
import br.com.wallet.conta.publisher.event.TransacaoEvent;
import br.com.wallet.conta.publisher.event.Transferencia;
import br.com.wallet.conta.service.ContaService;
import br.com.wallet.conta.service.TransacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {
    private final ContaService service;
    private final RabbitMqPublisher publisher;

    @Override
    public void realizarTransacao(TransacaoRequest transacaoRequest) throws JsonProcessingException {
        this.realizarMovimentacaoFincanceira(transacaoRequest);

        this.publisher.enviarRabbitMQ(this.transacaoEventFactory(transacaoRequest));
    }

    @Transactional
    private void realizarMovimentacaoFincanceira(TransacaoRequest transacaoRequest) {
        if (!transacaoRequest.getTipoTransacao().equals(TipoTransacao.SAQUE)) {
            this.service.realizarMovimentacaoConta(transacaoRequest.getContaOrigem().getNumeroConta(),
                    transacaoRequest.getContaDestino().getNumeroConta(),
                    transacaoRequest.getValorTransacao());
            return;
        }

        this.service.realizarMovimentacaoConta(transacaoRequest.getContaOrigem().getNumeroConta(),
                null,
                transacaoRequest.getValorTransacao());
    }

    private TransacaoEvent transacaoEventFactory(TransacaoRequest transacaoRequest) {

        if (transacaoRequest.getTipoTransacao().equals(TipoTransacao.SAQUE)) {
            return Saque.builder()
                    .tipoTransacao(transacaoRequest.getTipoTransacao())
                    .contaOrigem(transacaoRequest.getContaOrigem().getNumeroConta())
                    .agenciaOrigem(transacaoRequest.getContaOrigem().getNumeroAgencia())
                    .valorDebitado(transacaoRequest.getValorTransacao())
                    .terminal("TERMINAL PICPAY")
                    .timestamp(OffsetDateTime.now())
                    .build();
        } else if (transacaoRequest.getTipoTransacao().equals(TipoTransacao.TRANSFERENCIA)) {
            return Transferencia.builder()
                    .tipoTransacao(TipoTransacao.TRANSFERENCIA)
                    .valorDebitado(transacaoRequest.getValorTransacao())
                    .contaDestino(transacaoRequest.getContaDestino().getNumeroConta())
                    .agenciaDestino(transacaoRequest.getContaDestino().getNumeroAgencia())
                    .contaOrigem(transacaoRequest.getContaOrigem().getNumeroConta())
                    .agenciaOrigem(transacaoRequest.getContaOrigem().getNumeroAgencia())
                    .timestamp(OffsetDateTime.now())
                    .build();
        }
        return Deposito.builder()
                .tipoTransacao(TipoTransacao.DEPOSITO)
                .valorDebitado(transacaoRequest.getValorTransacao())
                .contaDestino(transacaoRequest.getContaDestino().getNumeroConta())
                .agenciaDestino(transacaoRequest.getContaDestino().getNumeroAgencia())
                .contaOrigem(transacaoRequest.getContaOrigem().getNumeroConta())
                .agenciaOrigem(transacaoRequest.getContaOrigem().getNumeroAgencia())
                .timestamp(OffsetDateTime.now())
                .build();


    }
}
