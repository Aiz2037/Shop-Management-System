package DataMapper;

import org.mapstruct.Mapper;

import DTO.CartItemDTO;
import Entity.CartItem;

@Mapper(componentModel="spring")
public interface CartItemMapper {
	CartItemDTO toDTO (CartItem cartItem);
	CartItem toEntity(CartItemDTO cartItemDTO);
}
