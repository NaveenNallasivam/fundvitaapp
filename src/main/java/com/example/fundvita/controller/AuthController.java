package com.example.fundvita.controller;

import com.example.fundvita.entity.User;
import com.example.fundvita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user")
    public ResponseEntity<Map<String, Object>> getUserInfo(OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
       return ResponseEntity.ok(Map.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "role", user.getRole() != null ? user.getRole() : "USER",
                "image", user.getImage() != null ? user.getImage() : "default.png"
        ));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api/info")
    public ResponseEntity<Map<String, String>> getAdminInfo() {
        return ResponseEntity.ok(Map.of("message", "Admin-only info"));
    }

    @GetMapping("/api/home")
    public String getHome(OAuth2AuthenticationToken token) {
        return "Welcome User";
    }
}
