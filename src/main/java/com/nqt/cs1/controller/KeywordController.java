package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.repository.KeywordRepository;
import com.nqt.cs1.service.imp.KeywordServiceImp;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KeywordController {
    @Autowired
    private KeywordServiceImp keywordService;

    @GetMapping(value = "/addKey")
    public String addKey(Model model){
        model.addAttribute("newKeyword", new Keyword());
        return "cs2/addKey";
    }

    @PostMapping(value = "/addKey")
    public String postAddKey(@ModelAttribute("keyword") Keyword keyword){
        this.keywordService.saveKeyword(keyword);
        return "redirect:/screen-1";
    }

}
