package com.ibra.training.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class User extends BaseModel{

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String role_name;
    private String email;
    private String phone;
    private boolean enabled = true;

}
