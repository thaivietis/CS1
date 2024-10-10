package com.nqt.cs1.service;

import com.nqt.cs1.domain.User;
import com.nqt.cs1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserSevice {
    User getUserByEmail(String email);
}
