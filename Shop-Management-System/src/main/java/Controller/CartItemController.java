package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Service.CartItemService;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		this.cartItemService=cartItemService;
	}

}
