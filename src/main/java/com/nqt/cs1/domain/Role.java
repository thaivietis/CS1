package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private List<User> users;
    @NotBlank(message = "Không được để trống")
    @Size(min = 1, max = 100, message = "Tên không phù hợp")
    private String name;

    @NotBlank(message = "Không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
}
