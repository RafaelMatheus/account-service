package br.com.wallet.conta.exception;

public class ObjetoConsultaNotFoundException extends RuntimeException {

    public ObjetoConsultaNotFoundException(String msg) {
        super(msg);
    }

    public ObjetoConsultaNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
