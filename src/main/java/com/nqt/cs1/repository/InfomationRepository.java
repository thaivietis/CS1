package com.nqt.cs1.repository;

import com.nqt.cs1.domain.Infomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfomationRepository extends JpaRepository<Infomation, Integer> {
    Infomation findById(int id);
}
