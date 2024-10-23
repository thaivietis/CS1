package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "departments")
public class Department {
    @Valid
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Không được để trống")
    int id;
    @Column(columnDefinition = "MEDIUMTEXT")
    String depertmentId;
    @NotBlank(message = "Không được để trống")
    String name;
}