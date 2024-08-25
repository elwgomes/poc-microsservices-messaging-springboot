package br.com.elwgomes.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.elwgomes.application.entity.Product;
import br.com.elwgomes.application.entity.dto.ProductDTO;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  ProductDTO toDto(Product product);

  Product toEntity(ProductDTO productDTO);

  List<ProductDTO> toListDto(List<Product> products);

}
