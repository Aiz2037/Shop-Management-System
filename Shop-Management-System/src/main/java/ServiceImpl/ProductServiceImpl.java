package ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.ProductDTO;
import DataMapper.ProductMapper;
import Entity.Category;
import Entity.Product;
import Exception.AlreadyExistsException;
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
	private final ProductMapper productMapper;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository,ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
	}
	
	
	@Override
	public ProductDTO getProductById(Long productId) {
		
		Product product =productRepository.findById(productId)
				//.map(product-> mapToDTO(product))
				.orElseThrow(()-> new ResourcesNotFoundException("Product not found !!"));
		return productMapper.toDTO(product);
	}
	

	
	@Override
	public Product addProduct(AddProductRequest request) {
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
		.orElseGet(()->{
			Category newCategory = new Category();
			newCategory.setName(request.getCategory().getName());
			return categoryRepository.save(newCategory);
		});
		
		Optional.of(request).filter(product->!productRepository.existsByName(request.getName()))
		.orElseThrow(()->new AlreadyExistsException("Product already exists in database. Unable to add!"));
		
		return productRepository.save(mapToProduct(request,category));
	}
	
	private Product mapToProduct(AddProductRequest request, Category category) {
		Product newProduct = new Product();
		newProduct.setName(request.getName());
		newProduct.setPrice(request.getPrice());
		newProduct.setInventory(request.getInventory());		
		newProduct.setBrand(request.getBrand());
		newProduct.setCategory(category);
		return newProduct;
	}



	@Override
	public ProductDTO updateProduct(ProductUpdateRequest productUpdateRequest,Long productID) {
		
		ProductDTO productDTO=getProductById(productID);
		Product product = Optional.ofNullable(productMapper.toEntity(productDTO))
				.map(existingProduct->updateExistingProduct(existingProduct,productUpdateRequest))
				.map(productRepository::save)
				.orElseThrow(()->new ResourcesNotFoundException("Product not found"));
		return productMapper.toDTO(product);
	}

	private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest productUpdateRequest) {
		existingProduct.setName(productUpdateRequest.getName());
		existingProduct.setPrice(productUpdateRequest.getPrice());
		existingProduct.setBrand(productUpdateRequest.getBrand());
		existingProduct.setInventory(productUpdateRequest.getInventory());
		
		Category category = Optional.ofNullable(categoryRepository.findByName(productUpdateRequest.getCategory().getName()))
				.orElseGet(()-> {
				Category newCategory = new Category();
				newCategory.setName(productUpdateRequest.getCategory().getName());
				return categoryRepository.save(newCategory);
				});
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
