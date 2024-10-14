package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Role;
import com.nqt.cs1.domain.User;
import com.nqt.cs1.repository.RoleRepository;
import com.nqt.cs1.service.imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/create")
    public String createUser(Model model) {
        List<Role> role = this.roleRepository.findAll();
        model.addAttribute("roles", role);
        return "user/create";
    }

    @PostMapping("/user/create")
    public String postCreateUser(@ModelAttribute User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        this.userService.saveUser(newUser);
        return "redirect:/user";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        Optional<User> user = this.userService.getUserById(id);
        List<Role> role = this.roleRepository.findAll();
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        }else{
            model.addAttribute("user", new User());
        }
        model.addAttribute("roles", role);
        return "user/update";
    }

    @PostMapping("/user/update")
    public String postUpdateUser(@ModelAttribute User user) {
        Optional<User> userOptional = this.userService.getUserById(user.getId());
        if (userOptional.isPresent()) {
            User userUpdate = userOptional.get();
            userUpdate.setId(user.getId());
            userUpdate.setUsername(user.getUsername());
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userUpdate.setRole(user.getRole());
            this.userService.saveUser(userUpdate);
        }
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Optional<User> user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/delete";
    }

    @PostMapping("/user/delete")
    public String postDeleteUser(@ModelAttribute User user) {
        this.userService.deleteUserById(user.getId());
        return "redirect:/user";
    }
}
