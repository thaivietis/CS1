package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String keywordSearch;
    private String keywordMatch;
    private int performance;
    private String platform;
    private String pattern;
    private String device;
    private String remarks;
}
