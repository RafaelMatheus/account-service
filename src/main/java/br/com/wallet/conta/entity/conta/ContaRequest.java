package br.com.wallet.conta.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest {
    private UUID idAgencia;
    private UUID idUsuario;
}
