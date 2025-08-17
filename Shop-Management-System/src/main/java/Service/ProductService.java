package Service;

import java.util.List;

import DTO.ProductDTO;
import Entity.Product;


public interface ProductService {
	Product insertProductDetail(ProductDTO productDTO);
	List<ProductDTO> viewAllProducts();
	ProductDTO updateProductName(String productName, String newproductName);
	ProductDTO updateProductPrice(String productName, double newproductPrice);
	void deleteByProductName(String productName);
}
