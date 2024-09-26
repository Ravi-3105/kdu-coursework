package com.kdu.smarthome.dao;

import com.kdu.smarthome.dto.request.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    UserDTO findByUsername(String username);
}
