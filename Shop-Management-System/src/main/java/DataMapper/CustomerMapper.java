package DataMapper;

import java.util.List;
import org.mapstruct.Mapper;
import DTO.CustomerDTO;
import Entity.Customer;

@Mapper(componentModel="spring")
public interface CustomerMapper {

	Customer toEntity(CustomerDTO customerDTO);
	CustomerDTO toDTO(Customer customer);
	List<CustomerDTO> toDTO(List<Customer> customer);

}
