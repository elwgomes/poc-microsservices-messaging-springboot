package br.com.elwgomes.application.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.com.elwgomes.application.entity.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor // only to @builder work
@NoArgsConstructor
@Getter
@Setter
public class Order implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
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
