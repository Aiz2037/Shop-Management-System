package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	void deleteByName(String name);

	Customer findByName(String name);

}
