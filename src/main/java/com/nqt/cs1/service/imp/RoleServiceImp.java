package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Role;
import com.nqt.cs1.repository.RoleRepository;
import com.nqt.cs1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRole() {
        return this.roleRepository.findAll();
    }
}
