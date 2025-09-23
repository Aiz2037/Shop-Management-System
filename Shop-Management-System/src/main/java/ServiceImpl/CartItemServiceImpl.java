package ServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
import Repository.CartItemRepository;
import Repository.CartRepository;
import Service.CartItemService;
import Service.CartService;
import Service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final ProductService productService;
	private final CartService cartService;
	private final CartRepository cartRepository;
	
	
	
	public CartItem addItemToCart(Long productID, Integer quantity, Long cartID) {
		//check cart already has the product
		//if no, create new cartItem and assign to cart
		//get total price for all items in the cart
		Cart cart = cartService.getCartByID(cartID);
		//check cartitem in the cart has the product
		CartItem cartItem = cart.getCartItems().stream()
				.filter(item->item.getProduct().getId().equals(productID)).findFirst().orElse(new CartItem());
		Product product = productService.getProductById(productID);
		
		if(cartItem.getId() == null) {
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setProductPrice(product.getPrice());
		}
		else {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
		}
		
		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		cart.setTotalAmount();
		cartRepository.save(cart);
		return cartItemRepository.save(cartItem);
	}
	
	//removeitemfrom cart
	//getNewTotalAmount
	public void removeItemFromCart(Long productID, Long cartID) {
		Cart cart=cartService.getCartByID(cartID);
		cart.getCartItems().stream().filter(item->item.getProduct().getId().equals(productID)).findFirst().ifPresent(cart::removeItem);
		cart.setTotalAmount();
		cartRepository.save(cart);
	}
	
	
}
