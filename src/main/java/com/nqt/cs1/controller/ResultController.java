package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.service.imp.KeywordServiceImp;
import com.nqt.cs1.service.imp.ResultServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ResultController {
    @Autowired
    private KeywordServiceImp keywordService;

    @Autowired
    private ResultServiceImp resultService;

    @GetMapping(value = "/screen-1")
    public String showScreen1(Model model){
        List<Keyword> keywords = this.keywordService.findAllKeywords();
        model.addAttribute("keywords", keywords);
        return "cs2/screenResult1";
    }

    @GetMapping(value = "/screen-2")
    public String showScreen2(Model model){
        List<Result> resultList = this.resultService.getAllResult();
        model.addAttribute("resultList", resultList);
        Map<Long, String[]> suggestionsMap = new HashMap<>();
        for(Result result : resultList){
            String[] suggestionsArray = this.resultService.processSuggestions(result);
            suggestionsMap.put(result.getId(), suggestionsArray);
        }
        model.addAttribute("suggestionsMap", suggestionsMap);
        return "cs2/screenResult2";
    }

    @GetMapping(value = "/screen-2")
    @ResponseBody
    public  Map<Long, String[]> showScreen2Josn(Model model){
        List<Result> resultList = this.resultService.getAllResult();
        Map<Long, String[]> suggestionsMap = new HashMap<>();
        for(Result result : resultList){
            String[] suggestionsArray = this.resultService.processSuggestions(result);
            suggestionsMap.put(result.getId(), suggestionsArray);
        }
//        model.addAttribute("suggestionsMap", suggestionsMap);
        return suggestionsMap;
    }
}
