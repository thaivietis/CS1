package com.nqt.cs1.repository;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findById(int id);
    Employee findByEmployeeId(String code);
    List<Employee> findAll();
    @Query("SELECT new com.nqt.cs1.dto.EmployeeInfomationDTO(" +
            "e.fullName, " +
            "e.avatar, " +
            "COALESCE(SUM(CASE WHEN i.type = \"Khen thưởng\" THEN 1 ELSE 0 END), 0), " +
            "COALESCE(SUM(CASE WHEN i.type = \"Kỷ luật\" THEN 1 ELSE 0 END), 0), " +
            "COALESCE(SUM(CASE WHEN i.type = \"Khen thưởng\" THEN 1 ELSE 0 END) - SUM(CASE WHEN i.type = \"Kỷ luật\" THEN 1 ELSE 0 END), 0), " +
            "e.department " +
            ")" +
            " FROM Infomation i " +
            "JOIN Employee e ON e.id = i.employee.id " +
            "JOIN Department d ON d.id = e.department.id " +
            "GROUP BY e.id, e.fullName "
    )
    List<EmployeeInfomationDTO> getEmployeeInfomation();
}
