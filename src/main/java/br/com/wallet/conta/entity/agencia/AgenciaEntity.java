package br.com.wallet.conta.entity.agencia;

import br.com.wallet.conta.entity.conta.ContaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "agencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroAgencia;
    private String descricao;
    @OneToMany(mappedBy = "agencia")
    private List<ContaEntity> contas;
}
