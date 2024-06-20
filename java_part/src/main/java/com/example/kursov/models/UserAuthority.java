package com.example.kursov.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    VIEW_PICTURES,
    CREATE_PICTURES,
    SAVE_PICTURES,
    MANAGE_PICTURES;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
