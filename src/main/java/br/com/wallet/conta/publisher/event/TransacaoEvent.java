package br.com.wallet.conta.publisher.event;

import br.com.wallet.conta.entity.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransacaoEvent {
    private TipoTransacao tipoTransacao;
    private String idUsuario;
    private String idConta;
}
