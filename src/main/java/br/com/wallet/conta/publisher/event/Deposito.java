package br.com.wallet.conta.publisher.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Deposito extends TransacaoEvent {
    private String terminal;
    private String contaDestino;
    private String agenciaDestino;
}
