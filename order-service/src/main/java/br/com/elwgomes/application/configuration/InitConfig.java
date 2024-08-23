package br.com.elwgomes.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.elwgomes.application.repository.ProductRepository;

@Configuration
@Profile("staging")
public class InitConfig implements CommandLineRunner {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public void run(String... args) throws Exception {

    // Product product = Product
    // .builder()
    // .name("TV")
    // .price(1200.00)
    // .build();
    //
    // productRepository.save(product);

    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }
}
