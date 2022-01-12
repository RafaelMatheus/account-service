package br.com.wallet.conta.service.impl;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.entity.enums.TipoTransacao;
import br.com.wallet.conta.mapper.ContaMapper;
import br.com.wallet.conta.publisher.RabbitMqPublisher;
import br.com.wallet.conta.publisher.event.TransacaoEvent;
import br.com.wallet.conta.repository.ContaRepository;
import br.com.wallet.conta.service.ContaService;
import br.com.wallet.conta.utils.ContaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {
    private final ContaMapper mapper;
    private final ContaRepository repository;
    private final RabbitMqPublisher publisher;

    @Override
    public ContaResponse criarConta(ContaRequest contaRequest) throws NoSuchAlgorithmException {
        String conta = ContaUtils.gerarNumeroAleatorio(5);

        ContaEntity contaMapper = this.mapper.paraContaEntity(contaRequest);

        contaMapper.setNumeroConta(conta);

        ContaEntity salvo = this.repository.save(contaMapper);

        publisher.enviarRabbitMQ(TransacaoEvent.builder()
                .idConta("teste")
                .idUsuario("teste")
                .tipoTransacao(TipoTransacao.SAQUE)
                .build());

        return this.mapper.paraResponse(salvo);
    }
}
