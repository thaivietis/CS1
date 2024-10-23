package com.nqt.cs1.dto;

import com.nqt.cs1.domain.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeInfomationDTO {
    String fullName;
    String avatar;
    long pointAchievement;
    long pointDisciplinary;
    long total;
    Department department;

    public EmployeeInfomationDTO(String john, String developer) {
    }
}
