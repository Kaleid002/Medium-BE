package com.example.test_backend.service;

import com.example.test_backend.Utils.JwtUtil;
import com.example.test_backend.dto.AuthResponseDto;
import com.example.test_backend.entity.Personalization;
import com.example.test_backend.entity.User;
import com.example.test_backend.respository.PersonalizationRepository;
import com.example.test_backend.respository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonalizationRepository personalizationRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PersonalizationRepository personalizationRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.personalizationRepository = personalizationRepository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto getGoogleDt(OAuth2User user) {

        String googleId = user.getAttribute("sub");
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        String provider = "google";
        String picture = user.getAttribute("picture");
        String refreshToken = jwtUtil.generateRefreshToken(googleId);
        String accessToken = jwtUtil.generateAccessToken(googleId);

        Optional<User> existUser = userRepository.findByGoogleId(googleId);


        if (existUser.isEmpty()) {
            User newUser = new User();
            newUser.setGoogleId(googleId);
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setProvider(provider);
            newUser.setCreateAt(LocalDateTime.now());

            newUser.setRefreshToken(refreshToken);
            userRepository.save(newUser);

            Personalization personalization = new Personalization();
            personalization.setPersonalizationUserId(newUser);
            personalization.setAvatar(picture);
            personalizationRepository.save(personalization);

            return new AuthResponseDto(newUser, personalization, accessToken, true);
        } else {
            User accountDt = existUser.get();
            Optional<Personalization> personalization = Optional.ofNullable(personalizationRepository.findByPersonalizationUserId(accountDt));

            accountDt.setRefreshToken(refreshToken);
            userRepository.save(accountDt);

            return new AuthResponseDto(accountDt, personalization.orElse(null), accessToken, false);
        }
    }

    public void deleteRefreshToken(String refreshToken, HttpServletResponse response) {
        String googleId = String.valueOf(jwtUtil.getGoogleIdFromToken(refreshToken).get("sub"));
        Optional<User> existUser = userRepository.findByGoogleId(googleId);
        existUser.ifPresent(user -> {
            user.setRefreshToken(null);
            userRepository.save(user);
        });

        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        response.addHeader("Set-Cookie", refreshTokenCookie.toString());
    }
}