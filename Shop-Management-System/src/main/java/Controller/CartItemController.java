package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.CartItemDTO;
import Service.CartItemService;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		this.cartItemService=cartItemService;
	}
	
	@PostMapping("/addToCart/{productID}/{quantity}")
	public CartItemDTO addToCart(@RequestParam ("productID") Long productID, @RequestParam("quantity") double quantityToPurchase) {
		return cartItemService.addToCart(productID, quantityToPurchase);
	}
	
	@DeleteMapping("/deleteCartItem")
	public void deleteCartItem(@RequestParam String productName) {
		cartItemService.deleteCartItem(productName);
	}
}
