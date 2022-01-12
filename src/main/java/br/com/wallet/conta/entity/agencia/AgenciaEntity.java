package br.com.wallet.conta.entity.agencia;

import br.com.wallet.conta.entity.conta.ContaEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "agencia")
public class AgenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agencia;
    private String descricao;
    @OneToMany(mappedBy = "agencia")
    private List<ContaEntity> contas;
}
