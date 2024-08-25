package br.com.elwgomes.application.entity.dto;

import java.util.Set;

import lombok.Data;

@Data
public class OrderDTO {
  private Set<OrderItemDTO> items;
}
