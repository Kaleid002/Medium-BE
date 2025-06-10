package com.example.test_backend.dto;

import com.example.test_backend.entity.Personalization;
import com.example.test_backend.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
    private Boolean isNewUser;
    private String googleId;
    private String name;
    private String email;
    private String provider;
    private String picture;
    private String refreshToken;
    private String accessToken;

    public AuthResponseDto(User user, Personalization personalization, String accessToken, boolean isNewUser) {
        this.isNewUser = isNewUser;
        this.googleId = user.getGoogleId();//
        this.name = user.getName();
        this.email = user.getEmail();
        this.provider = user.getProvider();
        this.picture = personalization != null ? personalization.getAvatar() : null;
        this.refreshToken = user.getRefreshToken();
        this.accessToken = accessToken;
    }
}
