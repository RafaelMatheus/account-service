package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.exception.NotFoundException;
import br.com.wallet.conta.mapper.ContaMapper;
import br.com.wallet.conta.repository.ContaRepository;
import br.com.wallet.conta.service.ContaService;
import br.com.wallet.conta.utils.ContaUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static br.com.wallet.conta.constants.MessageConstants.OBJECT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {
    private final ContaMapper mapper;
    private final ContaRepository repository;

    @Override
    public ContaResponse criarConta(ContaRequest contaRequest) throws NoSuchAlgorithmException, JsonProcessingException {
        String conta = ContaUtils.gerarNumeroAleatorio(5);

        ContaEntity contaMapper = this.mapper.paraContaEntity(contaRequest);

        contaMapper.setNumeroConta(conta);

        ContaEntity salvo = this.repository.save(contaMapper);

        return this.mapper.paraResponse(salvo);
    }

    @Override
    public ContaResponse buscarContaPorId(Long id) {
        return this.mapper.paraResponse(this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(OBJECT_NOT_FOUND)));
    }
}
