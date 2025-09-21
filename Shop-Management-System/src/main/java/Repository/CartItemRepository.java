package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	void deleteByProductName(String productName);

}
