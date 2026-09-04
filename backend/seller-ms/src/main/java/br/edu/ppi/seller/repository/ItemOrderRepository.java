package br.edu.ppi.seller.repository;

import br.edu.ppi.seller.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {
}
