package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.service.imp.KeywordServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private KeywordServiceImp keywordService;

    @GetMapping(value = "/screen-1")
    public String showScreen1(Model model){
        List<Keyword> keywords = this.keywordService.findAllKeywords();
        model.addAttribute("keywords", keywords);
        return "cs2/screenResult1";
    }

    @GetMapping(value = "/screen-2")
    public String showScreen2(Model model){
        List<Keyword> keywords = this.keywordService.findAllKeywords();
        model.addAttribute("keywords", keywords);
        return "cs2/screenResult2";
    }
}
