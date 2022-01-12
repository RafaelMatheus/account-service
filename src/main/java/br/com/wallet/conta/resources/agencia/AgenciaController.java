package br.com.wallet.conta.resources.agencia;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.NoSuchAlgorithmException;

public interface AgenciaController {
    @ApiOperation(value = "Criação de uma nova agencia")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    ResponseEntity<Void> criarAgencia(@RequestBody final AgenciaRequest agenciaRequest) throws NoSuchAlgorithmException;
}
