package com.example.fundvita.entity;

import javax.management.relation.Role;

public interface User {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    String getRole();

    void setRole(String role);

    String getImage();

    void setImage(String image);
}
