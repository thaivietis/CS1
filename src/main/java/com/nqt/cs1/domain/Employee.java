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
    private int id;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{10}$", message = "Employee ID phải có đúng 10 ký tự, bao gồm cả chữ và số")
    private String employeeId;

    @NotBlank(message = "Tên không phù hợp")
    @Size(min = 10, max = 200, message = "Tên không phù hợp")
    private String fullName;

    private Boolean gender;

    private String avatar;

    @PastOrPresent(message = "Không thể lựa chọn ngày tiếp theo")
    private LocalDate dateOfBirth;

    private Double salary;

    private int level;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String email;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Department department;
}