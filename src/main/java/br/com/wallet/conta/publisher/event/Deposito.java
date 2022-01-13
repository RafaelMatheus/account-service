package br.com.wallet.conta.publisher.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposito extends TransacaoEvent {
    private String terminal;
}
