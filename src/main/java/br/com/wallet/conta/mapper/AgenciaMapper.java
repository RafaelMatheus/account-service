package br.com.wallet.conta.mapper;

import br.com.wallet.conta.entity.agencia.AgenciaEntity;
import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AgenciaMapper {
    AgenciaEntity paraEntity(AgenciaRequest agencia);

    AgenciaResponse paraResponse(AgenciaEntity agencia);
}
