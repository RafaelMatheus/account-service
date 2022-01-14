package br.com.wallet.conta.exception;

public class SaldoInsufienteException extends RuntimeException {

    public SaldoInsufienteException(String msg) {
        super(msg);
    }

    public SaldoInsufienteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
