package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany(mappedBy = "keyword")
    @ToString.Exclude
    private List<Result> results;
}
