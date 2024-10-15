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

    @NotBlank(message = "Không được để trống")
    @Email(message = "Tài khoản không chính xác")
    private String username;

    @NotBlank(message = "Không được để trống")
    @Min(value = 8, message = "Tài khoản không chính xác")
    private String password;

    @NotBlank(message = "Không được để trống")
    @ManyToOne
    @JoinColumn(name = "roles")
    private Role role;
}
