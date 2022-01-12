package br.com.wallet.conta.resources.agencia;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AgenciaController {
    @ApiOperation(value = "Criação de uma nova agencia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<AgenciaResponse> criarAgencia(@RequestBody final AgenciaRequest agenciaRequest);
}
