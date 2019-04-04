package com.example.oauthexample.service;

import com.example.oauthexample.model.CustomUser;
import com.example.oauthexample.model.User;
import com.example.oauthexample.model.UserEntity;
import com.example.oauthexample.model.UserRole;
import com.example.oauthexample.repository.UserRepository;
import com.example.oauthexample.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        try {
            //Get the user from the DB
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User " + username + " was not found in the database");
            }
            //Get the users roles
            UserRole userRole = userRoleRepository.findTopByUsername(username);

            if (userRole == null) {
                throw new RoleInfoNotFoundException("Could not find role information regarding User " + username + " in the database");
            }

            userEntity.setUsername(username);
            userEntity.setPassword(user.getPassword());

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole());
            grantedAuthoritiesList.add(grantedAuthority);
            userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);

            return new CustomUser(userEntity);
        } catch (Exception e) {

        }

        return null;
    }
}