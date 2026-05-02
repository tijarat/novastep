package com.step.nova.repo;

import com.step.nova.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
}