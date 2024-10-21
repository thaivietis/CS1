package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.service.imp.KeywordServiceImp;
import com.nqt.cs1.service.imp.ResultServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ResultController {
    @Autowired
    private KeywordServiceImp keywordService;

    @Autowired
    private ResultServiceImp resultService;

    @GetMapping(value = "/screen-1")
    public String showScreen1(Model model) {
        List<Keyword> keywordList = this.keywordService.findAllKeywords();
        Boolean checkMatch = false;
        Map<Long, Boolean> checkMatchMap = new HashMap<>();
        for (Keyword keyword : keywordList) {
            List<Result> resultList = keyword.getResults();
            for (Result result : resultList) {
                String[] suggestionStr = this.resultService.processSuggestions(result);
                checkMatch = Arrays.stream(suggestionStr)
                        .anyMatch(s -> s.equals(keyword.getKeywordMatch()));
                checkMatchMap.put(result.getId(), checkMatch);
            }
        }
        model.addAttribute("checkMatchMap", checkMatchMap);
        model.addAttribute("keywords", keywordList);
        return "cs2/screenResult1";
    }


    @GetMapping(value = "/screen-2")
    public String showScreen2(@RequestParam(value = "date", required = false) String date,
                              Model model){
        List<Keyword> keywordListByMonth;
        LocalDate selectedDate;
        if (date != null && !date.isEmpty()) {
            selectedDate = LocalDate.parse(date + "-01");
            keywordListByMonth = this.keywordService.findAllByResultMonth(selectedDate.getYear(), selectedDate.getMonthValue());
            log.info("keywordListByMonth: {}", keywordListByMonth);
        } else {
            selectedDate = LocalDate.now();
            keywordListByMonth = this.keywordService.findAllByResultMonth(selectedDate.getYear(), selectedDate.getMonthValue());
        }
        Map<Long, String[]> suggestionsMap = new HashMap<>();
        for(Keyword keyword : keywordListByMonth){
            List<Result> resultList = keyword.getResults();
            for(Result result : resultList){
                String[] suggestionStr = this.resultService.processSuggestions(result);
                suggestionsMap.put(result.getId(), suggestionStr);
            }
        }
        model.addAttribute("suggestionsMap", suggestionsMap);
        model.addAttribute("keywordList", keywordListByMonth);
        return "cs2/screenResult2";
    }
}
