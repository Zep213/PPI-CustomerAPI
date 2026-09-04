package br.edu.ppi.seller.repository;

import br.edu.ppi.seller.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
