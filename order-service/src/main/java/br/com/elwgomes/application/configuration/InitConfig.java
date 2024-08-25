package br.com.elwgomes.application.configuration;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.elwgomes.application.entity.Order;
import br.com.elwgomes.application.entity.OrderItem;
import br.com.elwgomes.application.entity.Product;
import br.com.elwgomes.application.entity.enums.OrderStatus;
import br.com.elwgomes.application.repository.OrderItemRepository;
import br.com.elwgomes.application.repository.OrderRepository;
import br.com.elwgomes.application.repository.ProductRepository;

@Configuration
@Profile("staging")
public class InitConfig implements CommandLineRunner {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Override
  public void run(String... args) throws Exception {

    Product product = Product
        .builder()
        .name("TV")
        .price(1200.00)
        .build();

    productRepository.save(product);

    Order order = Order.builder()
        .moment(Instant.now())
        .orderStatus(OrderStatus.WAITING_PAYMENT)
        .build();
    OrderItem orderItem = new OrderItem(order, product, 1, product.getPrice());

    orderRepository.save(order);
    orderItemRepository.save(orderItem);

    // throw new UnsupportedOperationException("Unimplemented method 'run'");
  }
}
