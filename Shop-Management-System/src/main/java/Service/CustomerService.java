package Service;

import java.util.List;

import DTO.CustomerDTO;
import Entity.Customer;

public interface CustomerService {
	
	Customer insertCustomerDetail(CustomerDTO customerDTO);
	CustomerDTO viewCustomerDetailByName(String name);
	List<CustomerDTO> viewAllCustomers();
	void deleteCustomerByName(String name);
}
