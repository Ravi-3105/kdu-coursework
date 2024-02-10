package com.kdu.smarthome.controller;

import com.kdu.smarthome.config.UserConfig;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.dto.response.AuthResponse;
import com.kdu.smarthome.filter.TokenGenerator;
import com.kdu.smarthome.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication and reigistering controller
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private UserConfig userConfig;

    @Autowired
    AuthController(UserService userService, AuthenticationManager authenticationManager, UserConfig userConfig) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userConfig = userConfig;
    }

    @PostMapping("register")
    public HttpEntity<AuthResponse> addUser(@RequestBody UserDTO user, HttpServletResponse response) {
        String pass = user.getPassword();
        try {
            user.setRole("ROLE_USER");
            if(user.getName()==null)
            {
                user.setName(user.getFirstName()+user.getLastName());
            }
            userService.addUser(user);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),pass));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userConfig.loadUserByUsername(user.getUsername());

            String jwtToken= TokenGenerator.generateToken(authentication,response);
            if(jwtToken == null)
            {
                throw new NullPointerException("Null jwt token");
            }
            AuthResponse responseVal = new AuthResponse("User added successfully", jwtToken, HttpStatus.OK);
            return ResponseEntity.ok(responseVal);
        } catch (Exception e) {
            AuthResponse responseVal = new AuthResponse("Unable to add user"+e, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }
}
