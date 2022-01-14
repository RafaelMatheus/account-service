package br.com.wallet.conta.repository;

import br.com.wallet.conta.entity.agencia.AgenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciaRepository extends JpaRepository<AgenciaEntity, Long> {
    Optional<AgenciaEntity> findByNumeroAgencia(String numeroAgencia);
}
