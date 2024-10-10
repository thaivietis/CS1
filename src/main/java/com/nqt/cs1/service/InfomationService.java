package com.nqt.cs1.service;

import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.repository.InfomationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InfomationService {

    List<Infomation> getAll();

    Infomation getById(int id);

    Infomation saveInformation(Infomation information);

    void deleteById(int id);

    Infomation findById(int id);
}
