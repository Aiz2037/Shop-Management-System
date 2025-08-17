package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTO.ProductDTO;
import Entity.Product;
import Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/insertProduct")
	public Product insertProductDetail(@RequestBody ProductDTO productDTO) {
		return productService.insertProductDetail(productDTO);
	}
	
	@GetMapping("/getAllProducts")
	public List<ProductDTO> viewAllProducts() {
		return productService.viewAllProducts();
	}
	
	@PutMapping("/updateProductName")
	public ProductDTO updateProductName(@RequestParam String productName, @RequestParam String newProductName ) {
		return productService.updateProductName(productName, newProductName);
	}
	
	
	@PutMapping("/updateProductPrice")
	public ProductDTO updateProductPrice(@RequestParam String productName, @RequestParam double newProductPrice) {
		return productService.updateProductPrice(productName, newProductPrice);
	}
	
	@DeleteMapping("/deleteProductByName")
	public void deleteByProductName(@RequestParam String productName) {
		productService.deleteByProductName(productName);
	}
}
