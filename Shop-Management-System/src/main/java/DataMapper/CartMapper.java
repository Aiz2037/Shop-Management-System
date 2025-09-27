package DataMapper;
import org.mapstruct.Mapper;

import DTO.CartDTO;
import Entity.Cart;

@Mapper(componentModel="spring")
public interface CartMapper {

	
	Cart toEntity(CartDTO CartDTO);
	CartDTO toDTO(Cart cart);
	
	
}
