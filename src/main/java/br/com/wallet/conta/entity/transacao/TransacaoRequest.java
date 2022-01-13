package br.com.wallet.conta.entity.transacao;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {
    private Long idContaOrigem;
    private TipoTransacao tipoTransacao;
    private BigDecimal valorTransacao;
    private Long idContaDestino;
}
