package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.Cart;
import Exception.ResourcesNotFoundException;
import Response.APIResponse;
import Service.CartService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
		
	@GetMapping("/getCart")
	public ResponseEntity<APIResponse> getCartByID(@RequestParam Long cartID){
		try {
			Cart cart = cartService.getCartByID(cartID);		//even here already thrown exception
			//IT STILL EXECUTES NEXT BECAUSE OF TRY CATCH
			return ResponseEntity.ok(new APIResponse("Successfully get cart.",cart));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Unable to get cart",null));
		}
	}

	@DeleteMapping("/deleteCart")
	public ResponseEntity<APIResponse> deleteCartByID(@RequestParam Long cartID){
		try {
			cartService.deleteCartById(cartID);
			return ResponseEntity.ok(new APIResponse("Successfully deleted the cart.",null));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Unable to delete cart",null));
		}
	}
	
	@DeleteMapping("/deleteAllCart")
	public ResponseEntity<APIResponse> deleteAllCarts(){
		try {
			cartService.deleteAllCarts();
			return ResponseEntity.ok(new APIResponse("Successfully deleted all the carts.",null));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Unable to delete all the carts",null));
		}
	}
}
