package Service;

import java.util.List;

import Entity.Product;
import Request.AddProductRequest;
import Request.ProductUpdateRequest;


public interface ProductService {
	
	Product getProductById(Long productId);
	Product addProduct(AddProductRequest request);
	Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productID);
	List<Product> getProductsByBrandAndCategoryName(String productName, String CategoryName);
	void deleteProductById(Long productId);
	void deleteAllProducts();
	
}
