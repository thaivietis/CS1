package com.nqt.cs1.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentInformationDTO {
    String name;
    Long totalAchievements;
    Long totalDisciplinary;
    Long finalScore;

    public DepartmentInformationDTO(String name, Integer totalAchievements, Integer totalDisciplinary, Integer finalScore) {
        this.name = name;
        this.totalAchievements = Long.valueOf(totalAchievements);
        this.totalDisciplinary = Long.valueOf(totalDisciplinary);
        this.finalScore = Long.valueOf(finalScore);
    }
}
