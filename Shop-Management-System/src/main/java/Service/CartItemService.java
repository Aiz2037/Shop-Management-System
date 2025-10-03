package Service;

import Entity.CartItem;


public interface CartItemService {
	
	CartItem addItemToCart(Long productID, Integer quantity, Long cartID, Long userID);
	void removeAnItemFromCart(Long productID, Long cartID);
	void deleteAllCartItems();
}
