package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String keywordSearch;
    String keywordMatch;
    int performance;
    String platform;
    String pattern;
    String device;
    String remarks;
    @OneToMany(mappedBy = "keyword")
    @ToString.Exclude
    private List<Result> results;
}
