package com.example.test_backend.controller;

import com.example.test_backend.Utils.JwtUtil;
import com.example.test_backend.dto.UserLoginDto;
import com.example.test_backend.entity.Personalization;
import com.example.test_backend.entity.User;
import com.example.test_backend.respository.PersonalizationRepository;
import com.example.test_backend.respository.UserRepository;
import com.example.test_backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PersonalizationRepository personalizationRepository;

    public UserController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository,PersonalizationRepository personalizationRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.personalizationRepository = personalizationRepository;
    }

    @GetMapping("/login/googleDt")
    public UserLoginDto getGoogleUserData(@CookieValue(name = "access_token", required = false) String accessToken) {
        String googleId = (String) jwtUtil.getGoogleIdFromToken(accessToken).get("sub");
        User user = userRepository.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Personalization personalization = personalizationRepository.findByPersonalizationUserId(user);
        return new UserLoginDto(user, personalization);
    }

    @GetMapping("/signOut")
    public String signOut(@CookieValue(name = "refresh_token", required = false) String refreshToken, HttpServletResponse response) {
        userService.deleteRefreshToken(refreshToken, response);
        return null;
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }
}
