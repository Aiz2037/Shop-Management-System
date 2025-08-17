package ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CartDTO;
import DataMapper.CartMapper;
import Entity.Customer;
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
	
	@Override		//create parent before assigning it to child
	public CartDTO checkOutCart(String customerName) {
		CartDTO newCartDTO=new CartDTO();
		
		Customer getIndividualCustomer=customerRepository.findByName(customerName);
		newCartDTO.setCustomerName(getIndividualCustomer.getName());
		newCartDTO.setCustomerAddress(getIndividualCustomer.getAddress());
		cartRepository.save(cartMapper.toEntity(newCartDTO));
		return newCartDTO;
		
	}

}
