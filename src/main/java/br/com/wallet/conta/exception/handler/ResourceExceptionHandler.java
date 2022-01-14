package br.com.wallet.conta.exception.handler;

import br.com.wallet.conta.exception.ObjetoConsultaNotFoundException;
import br.com.wallet.conta.exception.SaldoInsufienteException;
import br.com.wallet.conta.exception.response.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjetoConsultaNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjetoConsultaNotFoundException e, HttpServletRequest request) {

        StandardError err = StandardError.builder()
                .error("Objeto não encontrado.")
                .path(request.getRequestURI())
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SaldoInsufienteException.class)
    public ResponseEntity<StandardError> saldoInsuficiente(SaldoInsufienteException e, HttpServletRequest request) {

        StandardError err = StandardError.builder()
                .error("Saldo insuficiente.")
                .path(request.getRequestURI())
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
