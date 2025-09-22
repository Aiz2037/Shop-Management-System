package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.CartItem;
import Exception.ResourcesNotFoundException;
import Response.APIResponse;
import Service.CartItemService;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		this.cartItemService=cartItemService;
	}
	
	@PostMapping("/addItemToCart")
	public ResponseEntity<APIResponse> addItemToCart(@RequestParam (required=false)Long cartID , 
													@RequestParam Long productID,
													@RequestParam Integer quantity){
		try {
			CartItem newItem = cartItemService.addItemToCart(productID, quantity, cartID);
			return ResponseEntity.ok(new APIResponse("Successfully added",newItem));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
			
		}
	}
	
	@DeleteMapping("/deleteCartItem")
	public ResponseEntity<APIResponse> removeItemFromCart(Long productID, Long cartID){
		try {
		cartItemService.removeItemFromCart(productID, cartID);
		return ResponseEntity.ok(new APIResponse("Succesfully deleted",null));}
		catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}

}
