package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.User;
import Exception.ResourcesNotFoundException;
import Request.AddUserRequest;
import Response.APIResponse;
import Service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/newUser")
	public ResponseEntity<APIResponse> createNewUser(@RequestBody AddUserRequest request){
		try {
			User user = userService.createUser(request);
			return ResponseEntity.ok(new APIResponse("User added successfully", user));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
		
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<APIResponse> getUserByID(@RequestParam Long userID){
		try {
			User user = userService.getUserById(userID);
			return ResponseEntity.ok(new APIResponse("User added successfully", user));
		} catch(ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
		
	}


	@DeleteMapping("/deleteAll")
	public ResponseEntity<APIResponse> deleteAll(){
		try {
			userService.deleteAllUser();
			return ResponseEntity.ok(new APIResponse("All users deleted successfully", null));
		}catch(ResourcesNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(),null));
		}
	}
	

}
