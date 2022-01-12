package br.com.wallet.conta.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ContaUtils {

    private ContaUtils() {

    }

    public static String gerarNumeroConta(int tamanho) throws NoSuchAlgorithmException {
        String conta = "";
        SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");

        for (int i = 0; i <= tamanho; i++) {
            conta += secureRandom.nextInt();
        }
        return conta;
    }
}
