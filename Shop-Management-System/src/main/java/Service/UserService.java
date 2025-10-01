package Service;

import Entity.User;
import Request.AddUserRequest;

public interface UserService {

	User getUserById(Long userID);

	void deleteUserById(Long userID);

	User createUser(AddUserRequest addUserRequest);

	void deleteAllUser();

}
