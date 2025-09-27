package DataMapper;

import org.mapstruct.Mapper;

import DTO.ProductDTO;
import Entity.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {
	ProductDTO toDTO (Product product);
	Product toEntity(ProductDTO product);
}
