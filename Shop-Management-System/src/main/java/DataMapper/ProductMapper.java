package DataMapper;

import java.util.List;

import org.mapstruct.Mapper;
import DTO.ProductDTO;
import Entity.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {
	
	Product toEntity(ProductDTO productDTO);
	ProductDTO toDTO(Product product);
	List<ProductDTO> toListDTO(List<Product> product);
}
