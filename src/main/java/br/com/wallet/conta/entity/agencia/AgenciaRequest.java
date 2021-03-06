package br.com.wallet.conta.entity.agencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaRequest {
    @NotEmpty
    private String descricao;
}
