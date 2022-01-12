package br.com.wallet.conta.resources.conta;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.service.ContaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("contas")
@ApiOperation("Teste")
@RequiredArgsConstructor
public class ContaControllerImpl implements ContaController {
    private final ContaService service;

    @Override
    public ResponseEntity<ContaResponse> criarConta(final ContaRequest contaRequest) throws NoSuchAlgorithmException {
        this.service.criarConta
                (contaRequest);
        return null;
    }
}
