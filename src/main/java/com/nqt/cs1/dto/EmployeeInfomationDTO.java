package com.nqt.cs1.dto;

import com.nqt.cs1.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfomationDTO {
    private String fullName;
    private String avatar;
    private long pointAchievement;
    private long pointDisciplinary;
    private long total;
    private Department department;
}
