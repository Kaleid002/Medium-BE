package com.example.test_backend.security;

import com.example.test_backend.dto.AuthResponseDto;
import com.example.test_backend.service.UserService;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Component
public class SecurityConfigImpl {
    private final UserService userService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfigImpl(UserService userService, JwtTokenFilter jwtTokenFilter) {
        this.userService = userService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/google-data", "/error").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                                .authorizationEndpoint(auth -> auth
                                        .baseUri("/oauth2/authorization/google")
                                )
                                .redirectionEndpoint(redir -> redir
                                        .baseUri("/login/oauth2/code/google")
                                )
                                .successHandler((req, resp, auth) -> {
                                            OAuth2User user = (OAuth2User) auth.getPrincipal();
                                            AuthResponseDto authResponse = userService.getGoogleDt(user);

                                            String accessToken = authResponse.getAccessToken();
                                            String refreshToken = authResponse.getRefreshToken();

                                    ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                                            .httpOnly(true)
                                            .secure(false)
                                            .path("/")
                                            .maxAge(Duration.ofDays(1))
                                            .build();

                                    ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                                            .httpOnly(true)
                                            .secure(false)
                                            .path("/")
                                            .maxAge(Duration.ofDays(30))
                                            .build();

                                    resp.addHeader("Set-Cookie", accessTokenCookie.toString());
                                    resp.addHeader("Set-Cookie", refreshTokenCookie.toString());
                                    resp.sendRedirect("http://localhost:4200/Forum");
                                        }

                                )
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
