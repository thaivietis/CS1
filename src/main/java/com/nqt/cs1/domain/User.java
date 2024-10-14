package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @Email
    private String username;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "roles")
    private Role role;
}
