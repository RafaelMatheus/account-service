package br.com.wallet.conta.entity.transacao;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {
    @Valid
    @NotNull
    private ContaTransacaoRequest contaOrigem;
    @NotNull
    private TipoTransacao tipoTransacao;
    @NotNull
    @Min(1)
    private BigDecimal valorTransacao;
    @Valid
    private ContaTransacaoRequest contaDestino;
}
