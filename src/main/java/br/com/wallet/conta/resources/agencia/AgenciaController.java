package br.com.wallet.conta.resources.agencia;

import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

public interface AgenciaController {
    @ApiOperation(value = "Criação de uma nova agencia")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<AgenciaResponse> criarAgencia(@RequestBody @Valid final AgenciaRequest agenciaRequest) throws NoSuchAlgorithmException;

    @ApiOperation(value = "Obter todas as agencias paginadas.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    Page<AgenciaResponse> encontrarAgencias(Pageable pageable);
}
