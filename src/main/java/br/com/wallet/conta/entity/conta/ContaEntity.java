package br.com.wallet.conta.entity.conta;

import br.com.wallet.conta.entity.agencia.AgenciaEntity;
import br.com.wallet.conta.entity.enums.StatusConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "conta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private AgenciaEntity agencia;
    private String idUsuario;
    private BigDecimal saldo;
    private StatusConta statusConta;
    @Column(unique = true)
    private String numeroConta;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
