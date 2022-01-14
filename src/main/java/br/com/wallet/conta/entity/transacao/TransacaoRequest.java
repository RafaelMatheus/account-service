package br.com.wallet.conta.entity.transacao;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {
    @Valid
    private ContaTransacaoRequest contaOrigem;
    @NotNull
    private TipoTransacao tipoTransacao;
    @NotNull
    private BigDecimal valorTransacao;
    @Valid
    private ContaTransacaoRequest contaDestino;
}
