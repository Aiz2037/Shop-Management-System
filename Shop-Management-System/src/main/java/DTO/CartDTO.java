package DTO;

import java.math.BigDecimal;
import java.util.Set;

import Entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	private Long id;				
	private Set<CartItem> cartItems; // = new HashSet<>();
	private BigDecimal totalAmount;
}
