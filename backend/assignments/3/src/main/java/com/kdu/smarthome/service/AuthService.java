package com.kdu.smarthome.service;

import com.kdu.smarthome.config.UserConfig;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.dto.response.AuthResponse;
import com.kdu.smarthome.filter.TokenGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserConfig userConfig;

    @Autowired
    public AuthService(UserService userService, AuthenticationManager authenticationManager,
                       UserConfig userConfig) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userConfig = userConfig;
    }

}
