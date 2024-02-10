package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.UserRepository;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.exception.custom.MyCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * SaveUser details
     * @param user
     */
    public void save(UserDTO user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            new MyCustomException("Error");
        }
    }

    /**
     * Add user
     * @param person
     */
    public void addUser(UserDTO person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        save(person);
    }

    /**
     * Find user
     * @param username
     * @return Found user details
     */
    public UserDTO getUserByUsername(String username) throws Exception {

        UserDTO user = userRepository.findByUsername(username.trim());
        try {
            if (user == null) {
                throw new MyCustomException("Username not found");
            }
        } catch (Exception e) {
            new MyCustomException("Error");
        }
        return user;
    }
}
