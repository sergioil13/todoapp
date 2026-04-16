package service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import model.User;
import model.UserRole;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder encoder;
	
	public User registerUser(CreateUserRequest request) {

	    return userRepository.save(
	            User.builder()
	                    .username(request.getUsername())
	                    .password(encoder.encode(request.getPassword()))
	                    .email(request.getEmail())
	                    .fullname(request.getFullname())
	                    .role(UserRole.USER)
	                    .build()
	    );
	    
	    
	}
	
	public User changeRole(User user, UserRole userRole) {
	    user.setRole(userRole);
	    return userRepository.save(user);
	}

	public User changeRole(Long userId, UserRole userRole) {
	    return userRepository.findById(userId)
	            .map(u -> {
	                u.setRole(userRole);
	                return userRepository.save(u);
	            }).orElse(null);
	}
	
	public List<User> findAll(){return userRepository.findAll(Sort.by("username"));}
}
