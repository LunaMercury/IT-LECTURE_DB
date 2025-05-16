package com.example.demo.domain.dto;

import com.example.demo.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String role;

    // DTO -> ENTITY
    public User toEntity() {
        return User
                .builder()
                .username(this.username)
                .password(this.password)
                .role("ROLE_USER")
                .build();
    }

    // ENTITY -> DTO
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
