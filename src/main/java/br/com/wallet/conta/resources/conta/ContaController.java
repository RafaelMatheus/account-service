package br.com.wallet.conta.resources.conta;

import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Api(tags = "Contas API")
public interface ContaController {
    @ApiOperation(value = "Criação de nova conta.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<ContaResponse> criarConta(@RequestBody final ContaRequest contaRequest) throws NoSuchAlgorithmException, JsonProcessingException;

    @ApiOperation(value = "Obter todas as contas de uma agencia")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{numeroAgencia}/agencias")
    Page<ContaResponse> encontrarContaPorAgencia(Pageable pageable, @PathVariable String numeroAgencia);
}
