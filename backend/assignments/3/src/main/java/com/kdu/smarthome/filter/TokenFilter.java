package com.kdu.smarthome.filter;

import com.kdu.smarthome.config.UserConfig;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Handles filtering of jwt toekn
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    UserConfig userConfig;

    public static final String JWT_HEADER = "Authorization";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJWTfromRequest(request);
        if (null != jwt ) {
            try {
                log.info(jwt);
                Claims claims = TokenGenerator.validateToken(jwt);
                String username = String.valueOf(claims.get("username"));

                UserDetails customUserDetailsObj = userConfig.loadUserByUsername(username);

                log.info(customUserDetailsObj.toString());
                Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetailsObj.getUsername(), null,
                        customUserDetailsObj.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!"+e);
            }

        }
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/api/v1/auth/**");
    }

    private String getJWTfromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER);
        if(bearerToken!=null &&  bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }
}
