package br.com.wallet.conta.service;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;

import java.security.NoSuchAlgorithmException;

public interface AgenciaService {
    AgenciaResponse criarAgencia(AgenciaRequest agenciaRequest) throws NoSuchAlgorithmException;
}
