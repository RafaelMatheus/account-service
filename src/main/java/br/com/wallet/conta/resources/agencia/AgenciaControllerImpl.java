package br.com.wallet.conta.resources.agencia;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agencias")
@ApiOperation("Teste")
public class AgenciaControllerImpl implements AgenciaController {

    @Override
    public ResponseEntity<AgenciaResponse> criarAgencia(final AgenciaRequest agenciaRequest) {
        return null;
    }
}
