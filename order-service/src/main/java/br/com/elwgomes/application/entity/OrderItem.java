package br.com.elwgomes.application.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem implements Serializable {
  private static final Long serialVersionUID = 1L;

  @EmbeddedId
  private OrderItemPK id = new OrderItemPK();

  private Integer quantity;
  private Double price;

  public OrderItem(Order order, Product product, Integer quantity, Double price) {
    id.setOrder(order);
    id.setProduct(product);
    this.quantity = quantity;
    this.price = price;
  }

  public Double getSubTotal() {
    return price * quantity;
  }
}
