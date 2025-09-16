package DTO;

import java.util.ArrayList;
import java.util.List;

import Entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	private List<String> purchasedItems = new ArrayList<String>();
	private double totalPrice;
	
}
