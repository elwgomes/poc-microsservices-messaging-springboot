package br.com.elwgomes.application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elwgomes.application.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
