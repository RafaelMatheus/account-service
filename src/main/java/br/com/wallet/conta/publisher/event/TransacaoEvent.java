package br.com.wallet.conta.publisher.event;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class TransacaoEvent {
    private TipoTransacao tipoTransacao;
    private Long idUsuario;
    private BigDecimal valorDebitado;
    private Long contaOrigem;
}
