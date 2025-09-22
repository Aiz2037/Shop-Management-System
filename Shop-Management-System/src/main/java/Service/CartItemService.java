package Service;

import Entity.CartItem;

public interface CartItemService {
	
	CartItem addItemToCart(Long productID, Integer quantity, Long cartID);
	void removeItemFromCart(Long productID, Long cartID);
}
