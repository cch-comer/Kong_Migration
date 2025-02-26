package com.kong.Kong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final DataSource dataSource;

    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    public CustomAuthenticationProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("SELECT username, password, active FROM users WHERE username = ?");
        jdbcDao.setAuthoritiesByUsernameQuery("SELECT username, type FROM users WHERE username = ?");

        UserDetails userDetails;
        try{
            userDetails = jdbcDao.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            logger.info(e.toString());
            throw new UsernameNotFoundException("Invalid username or password"); // username 정보가 없을때
        }

        if(userDetails.isEnabled()) {
            if(new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()); // 성공
            } else {
                logger.info("Invalid password to user : {}", username);
                throw new BadCredentialsException("Invalid username or password"); // 비밀번호가 틀렸을 때
            }
        } else {
            logger.info("disabled user : {}", username);
            throw new DisabledException("disabled user"); // 유저가 잠겼을 때
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}