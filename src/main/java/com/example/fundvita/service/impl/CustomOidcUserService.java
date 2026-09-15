package com.example.fundvita.service.impl;


import com.example.fundvita.entity.User;
import com.example.fundvita.entity.impl.UserImpl;
import com.example.fundvita.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import javax.management.relation.Role;
import java.util.*;

@Slf4j
@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserRepository userRepository;

    public CustomOidcUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);
        String name = oidcUser.getAttribute("name");
        String email = oidcUser.getAttribute("email");

        log.info("Loading user: name={}, email={}", name, email);

        UserImpl user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(UserImpl.builder()
                        .name(name)
                        .email(email)
                        .role("USER") // Default role
                        .build()));

        var authorities = Set.of(new SimpleGrantedAuthority(user.getRole() != null ? user.getRole() : "USER"));
        return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
