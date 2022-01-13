package br.com.wallet.conta.service;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.NoSuchAlgorithmException;

public interface ContaService {
    ContaResponse criarConta(ContaRequest contaRequest) throws NoSuchAlgorithmException, JsonProcessingException;

    ContaResponse buscarContaPorId(Long id);
}
