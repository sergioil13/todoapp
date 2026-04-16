package shared;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repository.UserRepository;

@Service("userDetailsService")
@RequiredArgsConstructor

public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername (String username) {
		return userRepository.findByUserNameOrEmail(username, username)
				.orElseThrow(()-> new UsernameNotFoundException("Bad credentials"));
				
	}
	
}
