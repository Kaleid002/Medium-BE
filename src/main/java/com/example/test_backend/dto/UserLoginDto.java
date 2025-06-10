package com.example.test_backend.dto;

import com.example.test_backend.entity.Personalization;
import com.example.test_backend.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String name;
    private String email;
    private String picture;

    public UserLoginDto(User user, Personalization personalization) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = personalization.getAvatar();
    }
}
