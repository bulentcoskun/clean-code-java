package victor.training.java8;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// get the list of users to UI

class UserFacade {
	
	private UserRepo userRepo;
	
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setFullName(user.getFirstName() + " " + user.getLastName().toUpperCase());
			dto.setActive(user.getDeactivationDate() == null);
			dtos.add(dto);
		}
		return dtos;
	}
}


// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
interface UserRepo {
	List<User> findAll();
}

@Data
class User {
	private String firstName;
	private String lastName;
	private String username;
	private LocalDate deactivationDate;
}

@Data
class UserDto {
	private String fullName;
	private String username;
	private boolean active;
}