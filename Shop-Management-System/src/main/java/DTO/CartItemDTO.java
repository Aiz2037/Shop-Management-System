package DTO;

import java.math.BigDecimal;

import Entity.Cart;
import Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //already generated getter and setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {	
	
	private Long id;
	private Product product;
	private Integer quantity;
	private BigDecimal productPrice;
	private BigDecimal totalPrice;
	private Cart cart;
	
}
