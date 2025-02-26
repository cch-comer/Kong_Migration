package com.kong.Kong.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@Component
@Configuration
public class CustomSuccessLoginHandler implements AuthenticationSuccessHandler {

    private final JdbcTemplate jdbcTemplate;

    public CustomSuccessLoginHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("success");
        String username = authentication.getName();
        Timestamp now = Timestamp.from(Instant.now());

        int updatedRows = jdbcTemplate.update(
                "UPDATE users SET last_login = ? WHERE username = ?",
                now, username
        );
        System.out.println(updatedRows);

    }
}
