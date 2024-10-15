package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private int id;

    @NotNull
    @Email
    private String username;

    @NotNull
    @Min(8)
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "roles")
    private Role role;
}
