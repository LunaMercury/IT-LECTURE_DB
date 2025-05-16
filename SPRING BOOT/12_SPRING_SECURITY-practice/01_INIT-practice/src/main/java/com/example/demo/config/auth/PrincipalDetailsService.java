package com.example.demo.config.auth;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// DB로부터 계정 정보를 받는 역할
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOption = userRepository.findById(username);
		if (userOption.isEmpty()) {
			throw new UsernameNotFoundException(username + "존재하지 않는 계정");
		}

		// entity -> dto
		UserDto userDto = UserDto.toDto(userOption.get());
		return new PrincipalDetails(userDto);

	}
}
