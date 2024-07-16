package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	Optional<SiteUser> findByusername(String username);
}
