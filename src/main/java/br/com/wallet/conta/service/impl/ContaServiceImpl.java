package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.entity.enums.StatusConta;
import br.com.wallet.conta.exception.ObjetoConsultaNotFoundException;
import br.com.wallet.conta.exception.SaldoInsufienteException;
import br.com.wallet.conta.mapper.ContaMapper;
import br.com.wallet.conta.repository.AgenciaRepository;
import br.com.wallet.conta.repository.ContaRepository;
import br.com.wallet.conta.service.ContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import static br.com.wallet.conta.constants.MessageConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {
    private final ContaMapper mapper;
    private final ContaRepository repository;
    private final AgenciaRepository agenciaRepository;

    @Override
    public Page<ContaResponse> encontrarContaPorAgencia(Pageable pageable, String numeroAgencia) {
        return this.repository.findByAgenciaNumeroAgencia(numeroAgencia, pageable)
                .map(this.mapper::paraResponse);
    }

    @Override
    public ContaResponse criarConta(ContaRequest contaRequest) throws NoSuchAlgorithmException {

        ContaEntity contaMapper = this.mapper.paraContaEntity(contaRequest);

        contaMapper.setAgencia(agenciaRepository.findByNumeroAgencia(contaRequest.getNumeroAgencia())
                .orElseThrow(() -> new ObjetoConsultaNotFoundException(AGENCIA_NAO_ENCONTRADA)));

        contaMapper.setDataCriacao(LocalDateTime.now());
        contaMapper.setStatusConta(StatusConta.ATIVA);
        contaMapper.setSaldo(BigDecimal.valueOf(0));

        ContaEntity salvo = this.repository.save(contaMapper);

        return this.mapper.paraResponse(salvo);
    }

    public void realizarTransferencia(String numeroContaOrigem, String numeroContaDestino, BigDecimal valor) {
        ContaEntity contaOrigem = this.obterConta(numeroContaOrigem);

        if (this.podeRealizarOperacao(contaOrigem, valor))
            throw new SaldoInsufienteException(SALDO_INSUFICIENTE);

        ContaEntity destino = this.obterConta(numeroContaDestino);

        destino.setSaldo(destino.getSaldo().add(valor));
        destino.setDataAtualizacao(LocalDateTime.now());

        this.repository.save(destino);

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaOrigem.setDataAtualizacao(LocalDateTime.now());

        this.repository.save(contaOrigem);
    }

    public void realizarSaque(String numeroContaOrigem, BigDecimal valor) {
        ContaEntity contaOrigem = obterConta(numeroContaOrigem);

        if (this.podeRealizarOperacao(contaOrigem, valor))
            throw new SaldoInsufienteException(SALDO_INSUFICIENTE);

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaOrigem.setDataAtualizacao(LocalDateTime.now());

        this.repository.save(contaOrigem);
    }

    public void realizarDeposito(String numeroContaDestino, BigDecimal valor) {
        ContaEntity contaDestino = obterConta(numeroContaDestino);

        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaDestino.setDataAtualizacao(LocalDateTime.now());

        this.repository.save(contaDestino);
    }

    private boolean podeRealizarOperacao(ContaEntity contaOrigem, BigDecimal valor) {
        return contaOrigem.getSaldo().compareTo(valor) == -1;
    }

    private ContaEntity obterConta(String numeroContaOrigem) {
        return this.repository.findByNumeroConta(numeroContaOrigem)
                .orElseThrow(() -> new ObjetoConsultaNotFoundException(CONTA_NAO_ENCONTRADA));
    }
}
