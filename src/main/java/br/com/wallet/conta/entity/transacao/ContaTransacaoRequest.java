package br.com.wallet.conta.entity.transacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaTransacaoRequest {
    @NotNull
    @Pattern(regexp = "\\d+")
    private String numeroConta;
    @NotNull
    @Pattern(regexp = "\\d+")
    private String numeroAgencia;
}
