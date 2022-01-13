package br.com.wallet.conta.mapper;

import br.com.wallet.conta.entity.conta.ContaEntity;
import br.com.wallet.conta.entity.conta.ContaRequest;
import br.com.wallet.conta.entity.conta.ContaResponse;
import br.com.wallet.conta.utils.ContaUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.security.NoSuchAlgorithmException;

@Mapper(imports = ContaUtils.class)
public interface ContaMapper {
    @Mappings({
            @Mapping(source = "idAgencia", target = "agencia.id"),
            @Mapping(target = "numeroConta", expression = "java(ContaUtils.gerarNumeroAleatorio(5))")
    })
    ContaEntity paraContaEntity(ContaRequest conta) throws NoSuchAlgorithmException;

    ContaResponse paraResponse(ContaEntity conta);
}
