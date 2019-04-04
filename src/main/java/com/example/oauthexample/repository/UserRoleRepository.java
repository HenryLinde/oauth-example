package com.example.oauthexample.repository;

import com.example.oauthexample.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findTopByUsername(String username);
}
