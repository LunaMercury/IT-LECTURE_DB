package com.example.demo.config.auth.provider;

import java.util.Map;
import java.util.Objects;

public interface OAuth2UserInfo {
    String getName();
    String getEmail();
    String getProvider();
    String getProviderId();
    Map<String, Objects> getAttributes();
}
