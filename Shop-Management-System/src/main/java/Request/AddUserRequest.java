package Request;

import lombok.Data;

@Data
public class AddUserRequest {
	
private Long id;
private String firstname;
private String lastname;
private String username;
private String email;
private String password;

}

