package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.Product;
import Exception.ResourcesNotFoundException;
import Request.AddProductRequest;
import Request.ProductUpdateRequest;
import Response.APIResponse;
import Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/addProduct/")
	public ResponseEntity<APIResponse> addNewProduct(@RequestBody AddProductRequest addProductRequest){
		Product newProduct=productService.addProduct(addProductRequest);
		return ResponseEntity.ok(new APIResponse("Product added successfully", newProduct));
	}
	
	@GetMapping("/viewSingleProduct/")
	public ResponseEntity<APIResponse> viewSingleProduct(@RequestParam Long productID){
		try{
			Product getProduct = productService.getProductById(productID);
			return ResponseEntity.ok(new APIResponse("Product is available", getProduct));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/updateMapping/")
	public ResponseEntity<APIResponse> updateProduct(@RequestParam ProductUpdateRequest productUpdateRequest, @RequestParam Long productID){
		try{
			Product updatedProduct = productService.updateProduct(productUpdateRequest, productID);
			return ResponseEntity.ok(new APIResponse("Product is available", updatedProduct));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/delete/")
	public ResponseEntity<APIResponse> deleteProduct(@RequestParam Long productID){
		try {
			Product toDeleteProduct = productService.getProductById(productID);
			if (toDeleteProduct != null) {
				productService.deleteProductById(productID);
				return ResponseEntity.ok(new APIResponse("Product successfully deleted",null));
			}
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Bad request",null));
	}
}
