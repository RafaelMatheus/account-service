package br.com.wallet.conta.repository;

import br.com.wallet.conta.entity.conta.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    Optional<ContaEntity> findByNumeroConta(String numeroConta);

    Page<ContaEntity> findByAgenciaNumeroAgencia(String numeroConta, Pageable pageable);
}
