package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    private String employeeId;

    @NotNull
    @Size(min = 10, max = 200)
    private String fullName;

    private Boolean gender;

    private String avatar;

    @PastOrPresent(message = "Không thể lựa chọn ngày tiếp theo")
    private LocalDate dateOfBirth;

    private Double salary;

    @Min(value = 1)
    @Max(value = 10)
    private int level;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@vietis\\.com$", message = "Email không hợp lệ")
    private String email;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Department department;
}