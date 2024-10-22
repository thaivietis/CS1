package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "informations")
public class Infomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "Không được để trống")
    String type;

    @NotBlank(message = "Không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    String reason;

    @NotNull(message = "Không được để trống")
    @PastOrPresent(message = "Ngày không được lựa chọn quá hiện tại")
    LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    Employee employee;
}
