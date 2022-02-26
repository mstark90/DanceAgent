package com.starkindustriesne.danceagent.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public abstract class DanceAgentService {

    protected Jwt getUser() {
        return (Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    protected String getCurrentUserId() {
        return getUser().getClaimAsString("uid");
    }

    protected String getCurrentUserName() {
        return getUser().getClaimAsString("name");
    }
}
