package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.agencia.AgenciaEntity;
import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import br.com.wallet.conta.mapper.AgenciaMapper;
import br.com.wallet.conta.repository.AgenciaRepository;
import br.com.wallet.conta.service.AgenciaService;
import br.com.wallet.conta.utils.ContaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgenciaServiceImpl implements AgenciaService {
    private final AgenciaMapper mapper;
    private final AgenciaRepository repository;

    @Override
    public AgenciaResponse criarAgencia(AgenciaRequest agenciaRequest) throws NoSuchAlgorithmException {
        AgenciaEntity agencia = this.mapper.paraEntity(agenciaRequest);

        this.repository.save(agencia);
        return this.mapper.paraResponse(agencia);
    }
}
