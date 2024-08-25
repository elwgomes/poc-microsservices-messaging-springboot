package br.com.elwgomes.application.entity.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderItemDTO {
  private UUID productId;
  private Integer quantity;
}
