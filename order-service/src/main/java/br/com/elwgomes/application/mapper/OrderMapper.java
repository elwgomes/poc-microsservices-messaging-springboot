package br.com.elwgomes.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.elwgomes.application.entity.Order;
import br.com.elwgomes.application.entity.dto.OrderDTO;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  Order toEntity(OrderDTO orderDto);

}
