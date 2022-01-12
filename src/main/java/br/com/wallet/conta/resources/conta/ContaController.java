package br.com.wallet.conta.resources.conta;

import br.com.wallet.conta.entity.conta.ContaRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Api(tags = "Contas API")
public interface ContaController {
    @ApiOperation(value = "Criação de nova conta.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    ResponseEntity<Void> criarConta(@RequestBody final ContaRequest contaRequest) throws NoSuchAlgorithmException;
}
