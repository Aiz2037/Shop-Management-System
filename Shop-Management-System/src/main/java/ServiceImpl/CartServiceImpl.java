package ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public Cart getCartByUserId(Long userID) {
		return Optional.ofNullable(cartRepository.findByUserId(userID))
				.orElseThrow(()->new ResourcesNotFoundException("Cart not found"));
	
	}
	
	@Override
	public Cart getCartByID(Long cartID) {
		return cartRepository.findById(cartID)
				.orElseThrow(()->new ResourcesNotFoundException("Cart not found")); //cannot find return null
		//return cartMapper.toDTO(cart);
	}
	
	@Override
	public void deleteCartById(Long cartID) {
		Optional.of(getCartByID(cartID)).ifPresent(cartRepository::delete);
	}
	
	@Override
	public void deleteAllCarts() {
		cartRepository.deleteAll();
	}
	
	@Override
	public Long initializeCart() {
		Cart newCart = new Cart();
//		Long cartID=cartIDgenerator.incrementAndGet();	Row was updated or deleted by another transaction
//		newCart.setId(cartID);
		return cartRepository.save(newCart).getId();
	}

}
