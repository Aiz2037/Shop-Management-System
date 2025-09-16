package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	Product findById(Integer productID);
	Product findByProductName(String productName);
	void deleteByProductName(String productName);

}
