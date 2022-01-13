package br.com.wallet.conta.mapper;

import br.com.wallet.conta.entity.agencia.AgenciaEntity;
import br.com.wallet.conta.entity.agencia.AgenciaRequest;
import br.com.wallet.conta.entity.agencia.AgenciaResponse;
import br.com.wallet.conta.utils.ContaUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.security.NoSuchAlgorithmException;

@Mapper(imports = ContaUtils.class)
public interface AgenciaMapper {
    @Mappings({
            @Mapping(target = "numeroAgencia", expression = "java(ContaUtils.gerarNumeroAleatorio(5))")
    })
    AgenciaEntity paraEntity(AgenciaRequest agencia) throws NoSuchAlgorithmException;

    AgenciaResponse paraResponse(AgenciaEntity agencia);
}
