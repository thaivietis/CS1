package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "results")
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "MEDIUMTEXT")
    String suggestions;
    LocalDate time;
    String image;
    @ManyToOne
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
