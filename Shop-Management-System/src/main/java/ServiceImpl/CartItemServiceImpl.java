package ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.analysis.function.Multiply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CartItemDTO;
import DataMapper.CartItemMapper;
import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
import Repository.CartItemRepository;
import Repository.CartRepository;
import Repository.ProductRepository;
import Service.CartItemService;
import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService {

	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	private final CartItemMapper cartItemMapper;
	private final CartRepository cartRepository;
	
	@Autowired
	public CartItemServiceImpl(ProductRepository productRepository,CartItemRepository cartItemRepository, CartItemMapper cartItemMapper, CartRepository cartRepository) {
		this.productRepository = productRepository;
		this.cartItemRepository = cartItemRepository;
		this.cartItemMapper = cartItemMapper;
		this.cartRepository = cartRepository;
		
	}
	
	@Override
	public CartItemDTO addToCart(Long cartId,String productName, Long quantityToPurchase) {
		
		Product productToPurchase = productRepository.findByProductName(productName);
		String selectedProductName = productToPurchase.getProductName();
		double selectedProductPrice = productToPurchase.getProductPrice();

		Multiply multiply = new Multiply();
		double totalPrice = multiply.value(quantityToPurchase,selectedProductPrice);
		
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setProductName(selectedProductName);
		cartItemDTO.setProductPrice(selectedProductPrice);
		cartItemDTO.setQuantity(quantityToPurchase);
		cartItemDTO.setTotalPrice(totalPrice);
		
		//assigning parent cart to child cart item
		Optional<Cart> assignCheckOutCart=cartRepository.findById(cartId);
		cartItemDTO.setCart(assignCheckOutCart.get());
		
		cartItemRepository.save(cartItemMapper.toEntity(cartItemDTO));
		
		return cartItemDTO;
	}
	
	public double calculateOverallPrice(Long foreignCartId) {
		List<CartItem> getAllCartItems = cartItemRepository.findAllByForeignCartId(foreignCartId);
		
		 double overallPrice = 0; 
		 Add add = new Add();
		 for(CartItem getIndividualCartItem:getAllCartItems) { 
			 double individualCartPrice = getIndividualCartItem.getProductPrice(); 
			 overallPrice = add.value(overallPrice,individualCartPrice); 
		}
		 return overallPrice;
	}

	@Override
	@Transactional
	public void deleteCartItem(String productName) {
		cartItemRepository.deleteByProductName(productName);
	}

}
