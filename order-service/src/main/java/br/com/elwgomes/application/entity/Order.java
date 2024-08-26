package br.com.elwgomes.application.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.elwgomes.application.entity.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_order")
@Data
public class Order implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant moment;
  private OrderStatus orderStatus;

  public Order(Instant moment, OrderStatus orderStatus) {
    this.moment = moment;
    this.orderStatus = orderStatus;
  }

  @OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<OrderItem> items = new HashSet<>();

  public Double getTotal() {
    double sum = 0;
    for (OrderItem x : items) {
      sum += x.getSubTotal();
    }
    return sum;
  }
}
