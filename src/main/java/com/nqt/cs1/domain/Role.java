package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    int id;

    @NotBlank(message = "Không được để trống")
    @Size(min = 1, max = 100, message = "Tên không phù hợp")
    String name;

    @NotBlank(message = "Không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    List<User> users;
}
