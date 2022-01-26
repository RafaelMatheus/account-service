package br.com.wallet.conta.service;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public interface ContaService {
    ContaResponse criarConta(ContaRequest contaRequest) throws NoSuchAlgorithmException, JsonProcessingException;

    Page<ContaResponse> encontrarContaPorAgencia(Pageable pageable, String numeroAgencia);

    void realizarDeposito(String numeroContaDestino, BigDecimal valor);

    void realizarTransferencia(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor);

    void realizarSaque(String numeroContaOrigem, BigDecimal valor);
}
