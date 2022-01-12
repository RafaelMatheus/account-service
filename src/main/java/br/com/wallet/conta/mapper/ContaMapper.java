package br.com.wallet.conta.mapper;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ContaMapper {
    ContaEntity paraContaEntity(ContaRequest conta);

    ContaResponse paraResponse(ContaEntity conta);
}
