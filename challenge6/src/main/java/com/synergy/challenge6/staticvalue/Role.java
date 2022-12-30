package com.synergy.challenge6.staticvalue;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    CUSTOMER,
    ;

    @Override
    public String getAuthority() {
        if(this == Role.ADMIN)
            return "admin";
        return "customer";
    }
}
