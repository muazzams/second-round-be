package dev.abidino.secondround.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityFilterChain {

    @Autowired
    @Lazy
    public WebSecurityFilterChain(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        authorize -> {
                            try {
                                authorize
                                        .requestMatchers(
                                                "/v3/api-docs",
                                                "/api/v1/auth/**",
                                                "/api/v1/cities/**",
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/swagger-ui.html",
                                                "/api/v1/auth/login")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .exceptionHandling()
                                        .authenticationEntryPoint((req, rsp, e) -> {
                                            if (rsp.getStatus() != 403) {
                                                rsp.sendError(401);
                                            }
                                        })
                                        .and()
                                        .addFilter(new ApiJWTAuthorizationFilter(authenticationManager))
                                        .sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and()
                                        .logout()
                                        .deleteCookies(SecurityConstant.TOKEN_COOKIE_NAME)
                                        .and()
                                        .csrf()
                                        .disable()
                                        .cors()
                                        .disable();

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
        return http.build();
    }
}