package ServiceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import Exception.AlreadyExistsException;
import Exception.ResourcesNotFoundException;
import Entity.User;
import Repository.UserRepository;
import Request.AddUserRequest;
import Service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public User getUserById(Long userID) {
		return userRepository.findById(userID)
				.orElseThrow(()-> new ResourcesNotFoundException("No user found !!"));
	}
	
	@Override
	public void deleteUserById(Long userID) {

		Optional.ofNullable(getUserById(userID)).ifPresentOrElse(userRepository::delete, ()->new ResourcesNotFoundException("no user found"));
	}
	
	@Override
	public void deleteAllUser() {
		userRepository.deleteAll();
	}
	
	@Override
	public User createUser(AddUserRequest addUserRequest) {
		return Optional.of(addUserRequest)
		.filter(user->!userRepository.existsByEmail(addUserRequest.getEmail()))
		.map( req->{	
			User user = new User();
			user.setUsername(addUserRequest.getUsername());
			user.setEmail(addUserRequest.getEmail());
			user.setLastname(addUserRequest.getLastname());
			user.setFirstname(addUserRequest.getFirstname());
			user.setPassword(addUserRequest.getPassword());
			return userRepository.save(user);})
		.orElseThrow(()->new AlreadyExistsException("User exists!"));	
	}
}
