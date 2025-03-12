package com.kong.Kong.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class CustomSuccessLoginHandler implements AuthenticationSuccessHandler {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(CustomSuccessLoginHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Timestamp now = Timestamp.from(Instant.now());

        try {
            int updatedRows = jdbcTemplate.update(
                    "UPDATE users SET last_login = ? WHERE username = ?",
                    now, username
            );

            if (updatedRows > 0) {
                logger.info("{} : update last_login success",username);
            } else {
                logger.error("{} : update last_login fail",username);
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }

    }
}
