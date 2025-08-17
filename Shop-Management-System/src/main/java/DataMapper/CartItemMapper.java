package DataMapper;

import java.util.List;

import org.mapstruct.Mapper;

import DTO.CartDTO;
import DTO.CartItemDTO;
import Entity.Cart;
import Entity.CartItem;

@Mapper(componentModel="spring")
public interface CartItemMapper {
	
	CartItem toEntity (CartItemDTO cartItemDTO);
	List<CartDTO> toListDTO (List<Cart> cart);
}
