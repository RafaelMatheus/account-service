package br.com.wallet.conta.publisher.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transferencia extends TransacaoEvent {
    private Long contaDestino;
}
