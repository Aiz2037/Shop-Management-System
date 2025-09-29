package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.Product;
import Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByEmail(String email);


}
