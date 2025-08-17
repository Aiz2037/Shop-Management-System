package DTO;

import java.util.List;

import Entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartDTO {
	
	private String customerName;
	private String customerAddress;
	private List<CartItem> cartItem;
	
//	public String getTotalPrice() {
//		return totalPrice;
//	}
//	public void setTotalPrice(String totalPrice) {
//		this.totalPrice = totalPrice;
//	}
	
	public List<CartItem> getCartItem() {
		return cartItem;
	}
	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
}
