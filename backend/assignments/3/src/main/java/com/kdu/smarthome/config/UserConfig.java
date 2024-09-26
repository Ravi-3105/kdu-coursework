package com.kdu.smarthome.config;

import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User Loading and checking user details present
 */
@Service
@Slf4j
public class UserConfig implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user;
        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User details not found for user: " + username + ". Please register first.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
