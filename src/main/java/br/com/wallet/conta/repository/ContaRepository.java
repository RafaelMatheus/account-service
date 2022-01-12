package br.com.wallet.conta.repository;

import br.com.wallet.conta.entity.conta.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<ContaEntity, UUID> {
}
