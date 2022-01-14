package br.com.wallet.conta.entity.transacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaTransacaoRequest {
    private String numeroConta;
    private String numeroAgencia;
}
