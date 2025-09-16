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
	public CartItemDTO addToCart(Long productID, double quantityToPurchase) {
		
		Optional<Product> productToPurchase = productRepository.findById(productID);
	
		CartItem cartItem = new CartItem();
		cartItem.setProductName(productToPurchase.get().getProductName());
		cartItem.setProductPrice(productToPurchase.get().getProductPrice());
		cartItem.setQuantity(quantityToPurchase);
		
		Multiply multiply = new Multiply();
		double totalPrice = multiply.value(productToPurchase.get().getProductPrice(), quantityToPurchase);
		cartItem.setTotalPrice(totalPrice);
		
		
		return null;
	}
	
	

	@Override
	@Transactional
	public void deleteCartItem(String productName) {
		cartItemRepository.deleteByProductName(productName);
	}

}
