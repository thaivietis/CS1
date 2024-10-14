package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.User;
import com.nqt.cs1.repository.UserRepository;
import com.nqt.cs1.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserSevice {
    @Autowired
    public UserRepository userRepository;

    public User getUserByEmail(String email){
        return this.userRepository.findByUsername(email);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User saveUser(User user){
        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(int id){
        return this.userRepository.findById(id);
    }

    public void deleteUserById(int id){
        this.userRepository.deleteById(id);
    }
}
