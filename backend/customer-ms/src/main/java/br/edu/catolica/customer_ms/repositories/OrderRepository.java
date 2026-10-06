package br.edu.catolica.customer_ms.repositories;

import br.edu.catolica.customer_ms.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderCode(String orderCode);

}
