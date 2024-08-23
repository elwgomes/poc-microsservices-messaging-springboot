package br.com.elwgomes.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elwgomes.application.entity.Product;
import br.com.elwgomes.application.mapper.ProductMapper;
import br.com.elwgomes.application.repository.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostMapping
  public ResponseEntity<?> saveProduct(@RequestBody Product product) {
    productRepository.save(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.toDto(product));
  }

  @GetMapping
  public ResponseEntity<?> getProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.INSTANCE.toListDto(productRepository.findAll()));
  }

}
