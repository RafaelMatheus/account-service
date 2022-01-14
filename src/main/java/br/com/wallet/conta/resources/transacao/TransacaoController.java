package br.com.wallet.conta.resources.transacao;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.transacao.TransacaoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

public interface TransacaoController {
    @ApiOperation(value = "Criação de uma nova agencia")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    ResponseEntity<Void> realizarTransacao(@RequestBody @Valid final TransacaoRequest transacaoRequest) throws NoSuchAlgorithmException, JsonProcessingException;
}
