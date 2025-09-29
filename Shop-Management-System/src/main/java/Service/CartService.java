package Service;

import DTO.CartDTO;
import Entity.Cart;

public interface CartService {

	Cart getCartByID(Long cartID);
	void deleteCartById(Long cartID);

	Long initializeCart();
	void deleteAllCarts();
	Cart getCartByUserId(Long userID);
}
