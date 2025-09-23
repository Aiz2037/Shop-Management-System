package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Exception.ResourcesNotFoundException;
import Response.APIResponse;
import Service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
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
