package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.Category;
import Entity.Product;

public interface CategoryRepository extends JpaRepository<Category,Long>{

	Category findByName(String name);

	boolean existsByName(String categoryName);

}
