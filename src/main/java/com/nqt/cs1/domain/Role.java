package com.nqt.cs1.domain;

import jakarta.persistence.*;
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
    @NotNull
    private List<User> users;
    @NotNull
    @Size(min = 1, max = 200)
    private String name;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
}
