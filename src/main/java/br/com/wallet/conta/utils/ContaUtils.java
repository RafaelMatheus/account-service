package br.com.wallet.conta.utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class ContaUtils {

    private ContaUtils() {

    }

    public static String gerarNumeroAleatorio(int tamanho) throws NoSuchAlgorithmException {
        LocalDateTime now = LocalDateTime.now();

        return now.toString().substring(0, tamanho);
    }
}
