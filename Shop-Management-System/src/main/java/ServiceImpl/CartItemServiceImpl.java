package ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CartItemDTO;
import DataMapper.CartItemMapper;
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
		
			return null;
	}
	
	

	@Override
	@Transactional
	public void deleteCartItem(String productName) {
		cartItemRepository.deleteByProductName(productName);
	}

}
