package com.example.springjpa.service;

import com.example.springjpa.entity.User;
import com.example.springjpa.exception.custom.MyCustomException;
import com.example.springjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save user.");
        }
    }

    public List<User> getUsers(UUID tenantId) {

        return userRepository.findByTenantId(tenantId);
    }

    public User getUserById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public void updateUser(UUID userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setTenant(user.getTenant());
            existingUser.setUpdatedBy(user.getUpdatedBy());
            existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(existingUser);
        } else {
            throw new MyCustomException("User with ID " + userId + " does not exist.");
        }
    }
    public int updateUserDetails(UUID userId, User user) {
        return userRepository.updateUserDetails(user.getUsername(),user.getTimeZone(),userId);
    }
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
