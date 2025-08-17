package Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.CustomerDTO;
import Entity.Customer;
import Service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@PostMapping("/insertCustomer")
	public Customer insertCustomerDetail(@RequestBody CustomerDTO customerDTO) {
		return customerService.insertCustomerDetail(customerDTO);
	}
	
	@GetMapping("/viewAllCustomer")		//admin access
	public List<CustomerDTO> viewAllCustomers(){
		return customerService.viewAllCustomers();
	}
	
	@GetMapping("/viewselectedCustomer")		
	public CustomerDTO viewSelectedCustomers(@RequestParam String name){
		return customerService.viewCustomerDetailByName(name);
	}
	
	@DeleteMapping("/deleteCustomer")	
	void deleteCustomerByName(@RequestParam String name) {
		customerService.deleteCustomerByName(name);
	}

}
