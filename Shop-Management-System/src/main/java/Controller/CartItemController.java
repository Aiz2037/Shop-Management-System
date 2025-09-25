package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.CartItem;
import Exception.ResourcesNotFoundException;
import Response.APIResponse;
import Service.CartItemService;
import Service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cartItem")
@RequiredArgsConstructor
public class CartItemController {
	
	private final CartItemService cartItemService;
	private final CartService cartService;
	
	@PostMapping("/addItemToCart")
	public ResponseEntity<APIResponse> addItemToCart(@RequestParam (required=false)Long cartID , 
													@RequestParam Long productID,
													@RequestParam Integer quantity){
		try {
			if(cartID==null) {
			cartID=cartService.initializeCart();	
			}
			
			CartItem newItem = cartItemService.addItemToCart(productID, quantity, cartID);
			return ResponseEntity.ok(new APIResponse("Successfully added",newItem));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
			
		}
	}
	
	@DeleteMapping("/deleteCartItem")
	public ResponseEntity<APIResponse> removeAnItemFromCart(@RequestParam Long productID, @RequestParam(required=false) Long cartID){
		try {
		cartItemService.removeAnItemFromCart(productID, cartID);
		return ResponseEntity.ok(new APIResponse("Succesfully deleted",null));}
		catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/deleteAllCartItem")
	public ResponseEntity<APIResponse> deleteAllCartItems(){
		try {
		cartItemService.deleteAllCartItems();
		return ResponseEntity.ok(new APIResponse("Succesfully deleted",null));}
		catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}

}
