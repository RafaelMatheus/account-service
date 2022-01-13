package br.com.wallet.conta.publisher.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recebimento extends TransacaoEvent {
    private BigDecimal valorCreditado;
}
