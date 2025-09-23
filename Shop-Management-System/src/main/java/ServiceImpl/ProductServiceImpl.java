package ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Category;
import Entity.Product;
import Exception.ResourcesNotFoundException;
import Repository.CategoryRepository;
import Repository.ProductRepository;
import Request.AddProductRequest;
import Request.ProductUpdateRequest;
import Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	
	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(()-> new ResourcesNotFoundException("Product not found !!"));
	}
	
	@Override
	public Product addProduct(AddProductRequest request) {
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
		.orElseGet(()->{
			Category newCategory = new Category();
			newCategory.setName(request.getCategory().getName());
			return categoryRepository.save(newCategory);
		});
		
		return productRepository.save(mapToProduct(request,category));
	}
	
	private Product mapToProduct(AddProductRequest request, Category category) {
		Product newProduct = new Product();
		newProduct.setName(request.getName());
		newProduct.setPrice(request.getPrice());
		newProduct.setBrand(request.getBrand());
		newProduct.setCategory(category);
		return newProduct;
	}



	@Override
	public Product updateProduct(ProductUpdateRequest productUpdateRequest,Long productID) {
		
		return Optional.ofNullable(getProductById(productID))
		.map(existingProduct->updateExistingProduct(existingProduct,productUpdateRequest))
		.map(productRepository::save)
		.orElseThrow(()->new ResourcesNotFoundException("Product not found"));
		
		
	}

	private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest productUpdateRequest) {
		existingProduct.setName(productUpdateRequest.getName());
		existingProduct.setPrice(productUpdateRequest.getPrice());
		existingProduct.setBrand(productUpdateRequest.getBrand());
		existingProduct.setInventory(productUpdateRequest.getInventory());
		
		Category category = categoryRepository.findByName(productUpdateRequest.getCategory().getName());
		existingProduct.setCategory(category);
		return existingProduct;
	}

	@Override
	public List<Product> getProductsByBrandAndCategoryName(String brandName, String categoryName) {
		return Optional.ofNullable(productRepository.findByBrandAndCategoryName(brandName, categoryName))
				.orElseThrow(()-> new ResourcesNotFoundException("Product not found"));
	}

	@Override
	public void deleteProductById(Long productId) {
		productRepository.findById(productId)
		.ifPresentOrElse(productRepository::delete, ()-> new ResourcesNotFoundException("Product not found"));
	}


	@Override
	public void deleteAllProducts() {
		productRepository.deleteAll();
		
	}
}
