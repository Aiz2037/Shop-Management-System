package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private String productName;
	private Long productPrice;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}
	public double getProductPrice() {
		return productPrice;
	}
}
