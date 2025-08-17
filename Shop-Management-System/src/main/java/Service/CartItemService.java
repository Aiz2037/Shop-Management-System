package Service;

import DTO.CartItemDTO;

public interface CartItemService {
	
	CartItemDTO addToCart(Long cartId, String productName,Long quantityToPurchase);
	double calculateOverallPrice(Long foreignCartItemId);
	void deleteCartItem(String productName);
	
}
