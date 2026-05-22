package br.com.brunojdsz.repositories;

import br.com.brunojdsz.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Share, Long> {
}
