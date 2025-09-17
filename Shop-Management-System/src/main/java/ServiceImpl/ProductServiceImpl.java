package ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DTO.ProductDTO;
import DataMapper.ProductMapper;
import Entity.Product;
import Exception.AlreadyExistsException;
import Exception.ProductNotFoundException;
import Repository.ProductRepository;
import Request.AddProductRequest;
import Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}
	
	
	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFoundException("Product not found !!"));
	}
	
	@Override
	public Product addProduct(AddProductRequest request) {
		
		Optional.ofNullable(productRepository.findByName(request.getName()))
		.orElseGet(()->{	
		return productRepository.save(mapToProduct(request));
		});
	}
	
	private Product mapToProduct(AddProductRequest request) {
		Product newProduct = new Product();
		newProduct.setName(request.getName());
		newProduct.setPrice(request.getPrice());
		newProduct.setCategory(request.getCategory());
		newProduct.setBrand(request.getBrand());
		return newProduct;
	}



	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Product getProductByCategoryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Product> getProductsByBrandAndCategoryName(String productName, String CategoryName) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteProductById(Long productId) {
		// TODO Auto-generated method stub
		
	}





}
