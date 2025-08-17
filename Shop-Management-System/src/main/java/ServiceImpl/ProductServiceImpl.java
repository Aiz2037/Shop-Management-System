package ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DTO.ProductDTO;
import DataMapper.ProductMapper;
import Entity.Product;
import Repository.ProductRepository;
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
	public Product insertProductDetail(ProductDTO productDTO) {
		Product newProduct = productMapper.toEntity(productDTO);
		return productRepository.save(newProduct);
	}
	
	@Override
	public List<ProductDTO> viewAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		List<ProductDTO> allProductsDTO = productMapper.toListDTO(allProducts);
		return allProductsDTO;
	}
	
	@Override
	public ProductDTO updateProductName(String productName, String newproductName) {
		Product productToUpdate = productRepository.findByProductName(productName);
		productToUpdate.setProductName(newproductName);
		Product updatedProduct = productRepository.save(productToUpdate);
		return productMapper.toDTO(updatedProduct);
	}

	@Override
	public ProductDTO updateProductPrice(String productName, double newproductPrice) {
		Product priceToUpdate = productRepository.findByProductName(productName);
		priceToUpdate.setProductPrice(newproductPrice);
		Product updatedPrice = productRepository.save(priceToUpdate);
		return productMapper.toDTO(updatedPrice);
	}

	@Override
	@Transactional
	public void deleteByProductName(String productName) {
		productRepository.deleteByProductName(productName);
	}

}
