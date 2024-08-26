package br.com.elwgomes.application.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elwgomes.application.entity.Order;
import br.com.elwgomes.application.entity.OrderItem;
import br.com.elwgomes.application.entity.Product;
import br.com.elwgomes.application.entity.dto.OrderDTO;
import br.com.elwgomes.application.entity.enums.OrderStatus;
import br.com.elwgomes.application.exceptions.ResourceNotFoundException;
import br.com.elwgomes.application.repository.OrderItemRepository;
import br.com.elwgomes.application.repository.OrderRepository;
import br.com.elwgomes.application.repository.ProductRepository;

@RestController
@RequestMapping("api/orders")
public class OrderController {

  final OrderRepository orderRepository;

  final OrderItemRepository orderItemRepository;

  final ProductRepository productRepository;

  final KafkaTemplate<String, Optional<Order>> kafkaTemplate;

  public OrderController(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
      ProductRepository productRepository, KafkaTemplate<String, Optional<Order>> kafkaTemplate) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.productRepository = productRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Value("${topics.order.request.topic}")
  private String orderTopic;

  @PostMapping
  public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) throws RuntimeException {

    Order order = new Order(Instant.now(), OrderStatus.WAITING_PAYMENT);
    orderRepository.save(order);

    orderDto.getItems().forEach(items -> {
      Product product = productRepository.findById(items.getProductId())
          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

      OrderItem orderItem = new OrderItem(order, product, items.getQuantity(), product.getPrice());
      orderItemRepository.save(orderItem);
      order.getItems().add(orderItem);
      orderRepository.save(order);
    });

    kafkaTemplate.send(orderTopic, orderRepository.findById(order.getId()));

    return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
  }

  @GetMapping
  public ResponseEntity<List<Order>> getOrders() {
    return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Optional<Order>> getOrderById(@PathVariable UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findById(id));
  }
}
