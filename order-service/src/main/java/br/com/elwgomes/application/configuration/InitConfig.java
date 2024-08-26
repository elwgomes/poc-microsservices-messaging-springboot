package br.com.elwgomes.application.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.elwgomes.application.entity.Order;
import br.com.elwgomes.application.repository.OrderItemRepository;
import br.com.elwgomes.application.repository.OrderRepository;
import br.com.elwgomes.application.repository.ProductRepository;

@Configuration
// @Profile("staging")
public class InitConfig {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Bean
  CommandLineRunner commandLineRunner(KafkaTemplate<String, Optional<Order>> kafkaTemplate) {
    return args -> {

      // Product product = new Product("TV", 1200.0);
      //
      // productRepository.save(product);
      //
      // Order order = new Order(Instant.now(), OrderStatus.WAITING_PAYMENT);
      //
      // OrderItem orderItem = new OrderItem(order, product, 1, product.getPrice());
      //
      // orderRepository.save(order);
      // orderItemRepository.save(orderItem);
      //
      // kafkaTemplate.send("order.request.topic.v1",
      // orderRepository.findById(order.getId()));
    };
  }

}
