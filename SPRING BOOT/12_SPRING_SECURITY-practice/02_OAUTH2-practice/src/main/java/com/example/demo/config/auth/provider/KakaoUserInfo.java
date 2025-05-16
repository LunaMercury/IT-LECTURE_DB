package com.example.demo.config.auth.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo {
    private Long id;
    private LocalDateTime connected_at;
    private Map<String,Object> properties;
    private Map<String,Object> kakao_account;

    @Override
    public String getName() {
        return properties.get("nickname").toString();
    }

    @Override
    public String getEmail() {
        return kakao_account.get("email").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return id+"";
    }

    @Override
    public Map<String, Objects> getAttributes() {
        return null;
    }
}
