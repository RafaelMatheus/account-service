package br.com.wallet.conta.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest {
    @Pattern(regexp = "\\d+")
    private String numeroAgencia;
    @Pattern(regexp = "\\d+")
    private String cpfUsuario;
}
