package br.com.wallet.conta.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest {
    private Long idAgencia;
    private Long idUsuario;
}
