package ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.CustomerDTO;
import DataMapper.CustomerMapper;
import Entity.Customer;
import Repository.CustomerRepository;
import Service.CustomerService;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
		this.customerRepository=customerRepository;
	}
	
	@Override
	public Customer insertCustomerDetail(CustomerDTO customerDTO) {
		return customerRepository.save(customerMapper.toEntity(customerDTO));
	}

	
	@Override
	public List<CustomerDTO> viewAllCustomers() {
		List<Customer> allCustomers= customerRepository.findAll();
		return customerMapper.toDTO(allCustomers);
	}

	@Override
	@Transactional
	public void deleteCustomerByName(String name) {
		customerRepository.deleteByName(name);
		
	}

	@Override
	public CustomerDTO viewCustomerDetailByName(String name) {
		Customer customerToView = customerRepository.findByName(name);
		return customerMapper.toDTO(customerToView);
		
	}
	
	
}
