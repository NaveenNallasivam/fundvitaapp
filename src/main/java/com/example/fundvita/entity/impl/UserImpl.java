package com.example.fundvita.entity.impl;

import com.example.fundvita.entity.User;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserImpl implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    private String image;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public void setRole(String role) {

    }

    @Override
    public String getImage() {
        return "";
    }

    @Override
    public void setImage(String image) {

    }
}
