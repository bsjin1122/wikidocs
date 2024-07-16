package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		// Config파일에서 빈으로 등록하여 사용하자. 
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 반드시 암호화하여 저장해야 한다. 비크립트 해시 함수 사용
		//user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
	
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
