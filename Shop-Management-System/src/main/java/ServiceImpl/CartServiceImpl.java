package ServiceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import Entity.Cart;
import Exception.ResourcesNotFoundException;
import Repository.CartRepository;
import Service.CartService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	
	public Cart getCartByID(Long cartID) {
		Cart cart = cartRepository.findById(cartID).orElseThrow(()->new ResourcesNotFoundException("Cart not found"));
		cart.setTotalAmount();
		return cartRepository.save(cart);
	}
	
	public void deleteCartById(Long cartID) {
		Optional.of(getCartByID(cartID)).ifPresent(cartRepository::delete);
	}
	
}
