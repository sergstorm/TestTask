package com.example.TestTask.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum  Role implements GrantedAuthority
{
    ADMIN,POSTER,VISITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
