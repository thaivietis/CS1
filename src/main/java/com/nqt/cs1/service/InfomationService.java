package com.nqt.cs1.service;

import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.repository.InfomationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfomationService {
    @Autowired
    private InfomationRepository informationRepository;

    public List<Infomation> getAll(){
        return this.informationRepository.findAll();
    }

    public Infomation getById(int id){
        return this.informationRepository.findById(id);
    }

    public Infomation saveInformation(Infomation information){
        return this.informationRepository.save(information);
    }

    public void deleteById(int id){
        this.informationRepository.deleteById(id);
    }

    public Infomation findById(int id){
        return this.informationRepository.findById(id);
    }
}
