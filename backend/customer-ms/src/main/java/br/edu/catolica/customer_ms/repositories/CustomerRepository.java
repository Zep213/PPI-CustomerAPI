package br.edu.catolica.customer_ms.repositories;

import br.edu.catolica.customer_ms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
