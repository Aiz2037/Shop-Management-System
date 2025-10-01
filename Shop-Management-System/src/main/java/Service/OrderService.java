package Service;

import Entity.Order;

public interface OrderService {

	Order placeOrder(Long userID);

	void deleteOrderById(Long orderID);

	void deleteAllOrder();

}
