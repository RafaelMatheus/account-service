package br.com.wallet.conta.service;

import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TransacaoService {
    void realizarTransacao(TransacaoRequest transacaoRequest) throws JsonProcessingException;
}
