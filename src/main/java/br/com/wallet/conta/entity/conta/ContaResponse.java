package br.com.wallet.conta.entity.conta;

import br.com.wallet.conta.entity.enums.StatusConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaResponse {
    private StatusConta statusConta;
    private String numeroConta;
}
