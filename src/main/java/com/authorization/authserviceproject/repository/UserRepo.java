package com.authorization.authserviceproject.repository;

import com.authorization.authserviceproject.models.Role;
import com.authorization.authserviceproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
        Optional<User> findByEmailEquals(String email);
}
