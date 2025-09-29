package ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import Entity.Cart;
import Entity.Order;
import Entity.OrderItem;
import Entity.Product;
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
	
	public Order placeOrder(Long userID) {
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDate.now());
		
		Cart cart = cartService.getCartByUserId(userID);
		order.setPriceToPay(cart.getTotalAmount());
		
		List<OrderItem> orderItems = cart.getCartItem().stream()
				.map(item-> {
					Product product = item.getProduct(); //access directdatabase
					product.setInventory(product.getInventory()-item.getQuantity());
					productRepository.save(product);
					orderItems.
					Integer quantity = item.getQuantity();
					BigDecimal productPrice = item.getProductPrice();
					BigDecimal totalPrice = item.getTotalPrice();
					
					return new OrderItem(product,quantity,productPrice,totalPrice);
					}).toList();
		
				
	}

}
