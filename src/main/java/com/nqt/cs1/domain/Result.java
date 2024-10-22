package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "MEDIUMTEXT")
    String suggestions;
    LocalDate time;
    String image;
    @ManyToOne
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
