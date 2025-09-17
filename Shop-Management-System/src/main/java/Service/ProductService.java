package Service;

import java.util.List;

import Entity.Product;
import Request.AddProductRequest;


public interface ProductService {
	
	Product getProductById(Long productId);
	Product addProduct(AddProductRequest request);
	Product updateProduct(Product product);
	List<Product> getProductsByBrandAndCategoryName(String productName, String CategoryName);
	void deleteProductById(Long productId);
	
}
