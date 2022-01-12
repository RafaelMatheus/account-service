package br.com.wallet.conta.entity.agencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaResponse {
    private String numeroAgencia;
    private String descricao;
}
