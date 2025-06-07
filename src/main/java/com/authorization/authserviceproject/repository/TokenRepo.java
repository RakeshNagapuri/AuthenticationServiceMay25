package com.authorization.authserviceproject.repository;

import com.authorization.authserviceproject.models.Role;
import com.authorization.authserviceproject.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token,Long> {
}
