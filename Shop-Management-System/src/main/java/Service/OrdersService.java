package Service;

import Entity.Orders;


public interface OrdersService {

	Orders placeOrder(Long userID);
	void deleteOrderById(Long orderID);
	void deleteAllOrder();

}
