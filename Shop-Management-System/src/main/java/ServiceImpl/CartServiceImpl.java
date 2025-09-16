package ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CartDTO;
import DataMapper.CartMapper;
import Entity.Cart;
import Repository.CartItemRepository;
import Repository.CartRepository;
import Repository.CustomerRepository;
import Service.CartItemService;
import Service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final CartMapper cartMapper;
	private final CustomerRepository customerRepository;
	private final CartItemService cartItemService;
	
	
	@Autowired
	public CartServiceImpl(CartItemRepository cartItemRepository,CartRepository cartRepository, CartMapper cartMapper, CustomerRepository customerRepository, CartItemService cartItemService) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.cartMapper = cartMapper;
		this.customerRepository = customerRepository;
		this.cartItemService = cartItemService;
	}
	
	@Override
	public CartDTO checkOutCart() {
		return null;
	}

	@Override
	public CartDTO createCart() {
		Cart newCart = new Cart();
		cartRepository.save(newCart);
		return null;
	}

}
