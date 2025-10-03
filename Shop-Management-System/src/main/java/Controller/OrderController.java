package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.Order;
import Response.APIResponse;
import Service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<APIResponse> placeOrder(@RequestParam Long userID){
		try{
			Order order = orderService.placeOrder(userID);
			return ResponseEntity.ok(new APIResponse("Order placed =)", order));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<APIResponse> deleteAllOrder(@RequestParam Long userID){
		try{
			orderService.deleteAllOrder();
			return ResponseEntity.ok(new APIResponse("All orders are deleted", null));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/deleteOrderById/{userID}")
	public ResponseEntity<APIResponse> deleteOrderByID(@PathVariable Long userID){
		try{
			orderService.deleteAllOrder();
			return ResponseEntity.ok(new APIResponse("Selected order has been deleted", null));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
}
