package com.kong.Kong.config;


import com.kong.Kong.security.CustomAuthenticationProvider;
import com.kong.Kong.security.CustomFailureLoginHandler;
import com.kong.Kong.security.CustomSuccessLoginHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomSuccessLoginHandler customSuccessLoginHandler;

    @Autowired
    private CustomFailureLoginHandler customFailureLoginHandler;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) -> csrfConfig.disable())
                .authorizeHttpRequests(auth -> auth // 허용 범위
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/loginPage"),
                                AntPathRequestMatcher.antMatcher("/assets/**"),
                                AntPathRequestMatcher.antMatcher("/error"),
                                AntPathRequestMatcher.antMatcher("/favicon.ico"),
                                AntPathRequestMatcher.antMatcher("/index.html"),
                                AntPathRequestMatcher.antMatcher("/robots.txt")

                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/loginPage")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessLoginHandler)
                        .failureHandler(customFailureLoginHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler(){
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                                    throws IOException, ServletException {
                                super.onLogoutSuccess(request, response, authentication);
                            }
                        })
                        .logoutSuccessUrl("/")
                        .deleteCookies("SESSION")
                        .permitAll()
                );
        return http.build();
    }
}
