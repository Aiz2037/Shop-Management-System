package Service;

import DTO.CartItemDTO;

public interface CartItemService {
	
	CartItemDTO addToCart(Long productID, double quantityToPurchase);

	void deleteCartItem(String productName);
	
}
