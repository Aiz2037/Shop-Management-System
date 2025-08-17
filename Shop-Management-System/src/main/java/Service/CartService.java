package Service;

import DTO.CartDTO;

public interface CartService {
	
	CartDTO checkOutCart(String customerName);

}
