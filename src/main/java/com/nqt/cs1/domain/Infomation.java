package com.nqt.cs1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "informations")
public class Infomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    private String type;

    @NotNull
    @Column(columnDefinition = "MEDIUMTEXT")
    private String reason;

    @NotNull
    @PastOrPresent(message = "Ngày không được lựa chọn quá hiện tại")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Employee employee;
}
