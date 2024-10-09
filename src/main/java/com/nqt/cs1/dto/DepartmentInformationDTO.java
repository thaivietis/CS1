package com.nqt.cs1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInformationDTO {
    private String name;
    private Long totalAchievements;
    private Long totalDisciplinary;
    private Long finalScore;

    public DepartmentInformationDTO(String name, Integer totalAchievements, Integer totalDisciplinary, Integer finalScore) {
        this.name = name;
        this.totalAchievements = Long.valueOf(totalAchievements);
        this.totalDisciplinary = Long.valueOf(totalDisciplinary);
        this.finalScore = Long.valueOf(finalScore);
    }
}
