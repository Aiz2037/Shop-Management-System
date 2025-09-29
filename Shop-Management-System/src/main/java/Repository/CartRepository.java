package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByForeingUserID(Long userID);
	
}
