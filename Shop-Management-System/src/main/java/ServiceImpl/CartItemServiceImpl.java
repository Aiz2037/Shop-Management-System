package ServiceImpl;

import org.springframework.stereotype.Service;

import DTO.ProductDTO;
import DataMapper.CartItemMapper;
import DataMapper.CartMapper;
import DataMapper.ProductMapper;
import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
import Repository.CartItemRepository;
import Repository.CartRepository;
import Service.CartItemService;
import Service.CartService;
import Service.ProductService;
import Service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final ProductService productService;
	private final CartService cartService;
	private final CartRepository cartRepository;
	private final ProductMapper productMapper;
	private final UserService userService;
	
	public CartItem addItemToCart(Long productID, Integer quantity, Long cartID,Long userID) {
		//check cart already has the product
		//if no, create new cartItem and assign to cart
		//get total price for all items in the cart
		Cart cart = cartService.getCartByID(cartID);
		cart.setUser(userService.getUserById(userID)); //set user id??
 		//check cartitem in the cart has the product
		CartItem cartItem = cart.getCartItem().stream()
				.filter(item->item.getProduct().getId().equals(productID)).findFirst().orElse(new CartItem());
		ProductDTO productDTO = productService.getProductById(productID);
		Product product = productMapper.toEntity(productDTO);
		
		if(cartItem.getId() == null) {
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setProductPrice(product.getPrice());
		}
		else {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
		}
		
		//CartItem cartItem=cartItemMapper.toEntity(cartItemDTO);
		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		cart.setTotalAmount();
		//cartRepository.save(cart);
		return cartItemRepository.save(cartItem); //save the cart data too , so weirdd
	
		
	}
	
	//removeitemfrom cart
	//getNewTotalAmount
	public void removeAnItemFromCart(Long productID, Long cartID) {
		Cart cart=cartService.getCartByID(cartID);
		cart.getCartItem().stream().filter(item->item.getProduct().getId().equals(productID)).findFirst().ifPresent(cart::removeItem);
		cart.setTotalAmount();
		cartRepository.save(cart);
	}
	
	public void deleteAllCartItems() {
		cartItemRepository.deleteAll();
	}
	
}
