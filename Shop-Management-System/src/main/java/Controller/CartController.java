package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.CartDTO;
import Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private final CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService=cartService;
	}
	
	@PostMapping("/checkout")
	public CartDTO addToCart(@RequestParam String customerName) {
		return cartService.checkOutCart(customerName);
	}
}
