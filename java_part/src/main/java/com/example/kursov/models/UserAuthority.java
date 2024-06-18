package com.example.kursov.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {

    /*
    VIEW_PICTURES: Могут просматривать каталог публичных изображений
    CREATE_PICTURES: Могут рисовать свои изображения и получать ответы и предыдущие функции
    SAVE_PICTURES: Могут сохранять изображения публично и приватно и предыдущие функции
    MANAGE_PICTURES: Админ
     */

    VIEW_PICTURES, // 0
    CREATE_PICTURES, // 1
    SAVE_PICTURES, // 2
    MANAGE_PICTURES; // 3

    @Override
    public String getAuthority() {
        return this.name();
    }
}
