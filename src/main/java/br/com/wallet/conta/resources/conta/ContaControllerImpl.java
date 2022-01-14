package br.com.wallet.conta.resources.conta;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.service.ContaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaControllerImpl implements ContaController {
    private final ContaService service;

    @Override
    public ResponseEntity<ContaResponse> criarConta(final ContaRequest contaRequest) throws NoSuchAlgorithmException, JsonProcessingException {
        return ResponseEntity
                .ok(this.service.criarConta(contaRequest));
    }

    @Override
    public Page<ContaResponse> encontrarContaPorAgencia(Pageable pageable, String numeroAgencia) {
        return this.service.encontrarContaPorAgencia(pageable, numeroAgencia);
    }
}
