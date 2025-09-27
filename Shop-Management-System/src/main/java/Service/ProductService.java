package Service;

import java.util.List;

import DTO.ProductDTO;
import Entity.Product;
import Request.AddProductRequest;
import Request.ProductUpdateRequest;


public interface ProductService {
	
	ProductDTO getProductById(Long productId);
	Product addProduct(AddProductRequest request);
	ProductDTO updateProduct(ProductUpdateRequest productUpdateRequest, Long productID);
	List<Product> getProductsByBrandAndCategoryName(String productName, String CategoryName);
	void deleteProductById(Long productId);
	void deleteAllProducts();
	
}
