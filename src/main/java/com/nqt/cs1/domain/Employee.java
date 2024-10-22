package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{10}$", message = "Employee ID phải có đúng 10 ký tự, bao gồm cả chữ và số")
    String employeeId;

    @NotBlank(message = "Tên không phù hợp")
    @Size(min = 10, max = 200, message = "Tên không phù hợp")
    String fullName;

    Boolean gender;

    String avatar;

    @PastOrPresent(message = "Không thể lựa chọn ngày tiếp theo")
    LocalDate dateOfBirth;

    Double salary;

    int level;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    String email;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    String phone;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    Department department;
}