package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.Category;
import Exception.ResourcesNotFoundException;
import Response.APIResponse;
import Service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	
	@PostMapping("/addCategory")
	public ResponseEntity<APIResponse> addCategory(@RequestParam String categoryName){
		try{
			Category addedCategory=categoryService.addCategory(categoryName);
			return ResponseEntity.ok(new APIResponse("Categpries added", addedCategory));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<APIResponse> updateCategory(@RequestParam String updatecategoryName,@RequestParam Long categoryID){
		try{
			Category updated=categoryService.updateCategory(updatecategoryName,categoryID);
			return ResponseEntity.ok(new APIResponse("Categpries added", updated));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/deleteCategoryById/")
	public ResponseEntity<APIResponse> deleteCAtegoryById(Long categoryID){
		try{
			categoryService.deleteCategoryById(categoryID);
			return ResponseEntity.ok(new APIResponse("Category has been deleted", null));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}

	
	@DeleteMapping("/deleteAllCategories/")
	public ResponseEntity<APIResponse> deleteAllCategories(){
		try{
			categoryService.deleteAllCategories();
			return ResponseEntity.ok(new APIResponse("All categories are deleted", null));
		}catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}

}
