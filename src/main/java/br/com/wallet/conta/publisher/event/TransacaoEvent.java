package br.com.wallet.conta.publisher.event;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import br.com.wallet.conta.serializer.OffsetDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class TransacaoEvent {
    private TipoTransacao tipoTransacao;
    private BigDecimal valorDebitado;
    private String contaOrigem;
    private String agenciaOrigem;
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime timestamp;
}