package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>{

}
