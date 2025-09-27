package ServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import DTO.CartDTO;
import DataMapper.CartMapper;
import Entity.Cart;
import Exception.ResourcesNotFoundException;
import Repository.CartRepository;
import Service.CartService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	//private final CartService cartService; //cause circular dependency
	//private final AtomicLong cartIDgenerator = new AtomicLong(0);
	private final CartMapper cartMapper;
	
	public Cart getCartByID(Long cartID) {
		return cartRepository.findById(cartID)
				.orElseThrow(()->new ResourcesNotFoundException("Cart not found")); //cannot find return null
		//return cartMapper.toDTO(cart);
	}
	
	public void deleteCartById(Long cartID) {
		Optional.of(getCartByID(cartID)).ifPresent(cartRepository::delete);
	}
	
	public void deleteAllCarts() {
		cartRepository.deleteAll();
	}
	
	public Long initializeCart() {
		Cart newCart = new Cart();
//		Long cartID=cartIDgenerator.incrementAndGet();	Row was updated or deleted by another transaction
//		newCart.setId(cartID);
		return cartRepository.save(newCart).getId();
	}
}
