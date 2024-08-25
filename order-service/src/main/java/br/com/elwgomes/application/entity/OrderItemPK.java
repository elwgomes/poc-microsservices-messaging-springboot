package br.com.elwgomes.application.entity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class OrderItemPK implements Serializable {
  private static final Long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

}
