package ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import Entity.Cart;
import Entity.Order;
import Entity.OrderItem;
import Entity.Product;
import Exception.ResourcesNotFoundException;
import Repository.CartRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Service.CartService;
import Service.OrderService;
import Status.OrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService  {
	
	private CartService cartService;
	private ProductRepository productRepository;
	private OrderRepository orderRepository;
	private CartRepository cartRepository;
	
	@Override
	public Order placeOrder(Long userID) {
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDate.now());
		Cart cart = cartService.getCartByUserId(userID);
		order.setPriceToPay(cart.getTotalAmount());
		
		List<OrderItem> orderItems = cart.getCartItem().stream()  //set orderitem cannot accept stream object data
				.map(item-> {
					//Product product = productRepository.findByName(item.getProduct().getName());
					Product product = item.getProduct(); //is it link directly to product database?
					product.setInventory(product.getInventory()-item.getQuantity());
					productRepository.save(product);
					Integer quantity = item.getQuantity();
					BigDecimal productPrice = item.getProductPrice();
					BigDecimal totalPrice = item.getTotalPrice();
					
					return new OrderItem(product,quantity,productPrice,totalPrice);
					}).toList();
		
		order.setOrderItems(new HashSet<>(orderItems));
		cartRepository.deleteById(cart.getId()); //clear cart after place order
		return orderRepository.save(order);		
	}
	
	@Override
	//deleteOrderById
	public void deleteOrderById(Long orderID) {
		orderRepository.findById(orderID).ifPresentOrElse(orderRepository::delete, 
				()->new ResourcesNotFoundException("Successfully deleted the product"));
	}
	
	@Override
	//deleteAllOrder
	public void deleteAllOrder() {
		orderRepository.deleteAll();
	}
}
