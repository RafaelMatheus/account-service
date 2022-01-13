package br.com.wallet.conta.resources.transacao;

import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import br.com.wallet.conta.service.TransacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacoes")
@Api(tags = "Transacao API")
@RequiredArgsConstructor
public class TransacaoControllerImpl implements TransacaoController {
    private final TransacaoService service;

    @Override
    public ResponseEntity<Void> realizarTransacao(TransacaoRequest transacaoRequest) throws JsonProcessingException {
        this.service.realizarTransacao(transacaoRequest);
        return ResponseEntity
                .noContent()
                .build();
    }
}
