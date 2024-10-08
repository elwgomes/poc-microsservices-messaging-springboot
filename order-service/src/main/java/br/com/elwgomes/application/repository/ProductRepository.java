package br.com.elwgomes.application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elwgomes.application.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
