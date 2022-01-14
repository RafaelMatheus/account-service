package br.com.wallet.conta.resources.agencia;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import br.com.wallet.conta.service.AgenciaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("agencias")
@Api(tags = "Agencia API")
@RequiredArgsConstructor
public class AgenciaControllerImpl implements AgenciaController {
    private final AgenciaService service;

    @Override
    public ResponseEntity<AgenciaResponse> criarAgencia(final AgenciaRequest agenciaRequest) throws NoSuchAlgorithmException {
        return ResponseEntity
                .ok(this.service.criarAgencia(agenciaRequest));
    }

    @Override
    public Page<AgenciaResponse> encontrarAgencias(Pageable pageable) {
        return this.service.encontrarAgencias(pageable);
    }
}
