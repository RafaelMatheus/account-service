package br.com.wallet.conta.resources.conta;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.NoSuchAlgorithmException;

public interface ContaController {
    @ApiOperation(value = "Criação de nova conta.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<ContaResponse> criarConta(@RequestBody final ContaRequest contaRequest) throws NoSuchAlgorithmException;
}
