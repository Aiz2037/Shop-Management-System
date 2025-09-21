package ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Category;
import Exception.AlreadyExistsException;
import Exception.ResourcesNotFoundException;
import Repository.CategoryRepository;
import Service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImplementation(CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}

	@Override
	public Category getCategoryById(Long categoryID) {
		return categoryRepository.findById(categoryID)
				.orElseThrow(()->new ResourcesNotFoundException("Category not found"));
	}

	@Override
	public Category addCategory(String categoryName) {
		if(!categoryRepository.existsByName(categoryName)) {
			Category newCategory=new Category();
			newCategory.setName(categoryName);
			return categoryRepository.save(newCategory);
		}else {
			throw new AlreadyExistsException("Category already exist");
		}
	
	}

	@Override
	public Category updateCategory(String updateCategoryName, Long categoryID) {
		return Optional.ofNullable(getCategoryById(categoryID))
		.map(oldCategory->{
			oldCategory.setName(updateCategoryName);
			return categoryRepository.save(oldCategory);
		}).orElseThrow(()->new ResourcesNotFoundException("Category is not found!"));
	}

	@Override
	public void deleteCategoryById(Long productID) {
		Optional.ofNullable(getCategoryById(productID)).
		ifPresentOrElse(categoryRepository::delete, ()-> new ResourcesNotFoundException("Category not found"));
		
	}

}
