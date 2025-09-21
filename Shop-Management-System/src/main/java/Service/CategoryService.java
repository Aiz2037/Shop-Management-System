package Service;

import Entity.Category;


public interface CategoryService {
	Category getCategoryById(Long categoryID);
	Category addCategory(String categoryName);
	Category updateCategory(String updateCategoryName, Long categoryID);
	void deleteCategoryById(Long productID);
	
}
