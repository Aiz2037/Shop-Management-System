package ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Repository.CartItemRepository;
import Repository.CartRepository;
import Service.CartItemService;
import Service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	
	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
}
