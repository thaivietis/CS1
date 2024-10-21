package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.repository.KeywordRepository;
import com.nqt.cs1.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeywordServiceImp implements KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public Keyword saveKeyword(Keyword keyword) {
        return this.keywordRepository.save(keyword);
    }

    @Override
    public List<Keyword> findAllKeywords() {
        return this.keywordRepository.findAll();
    }

    @Override
    public Keyword findById(long id){
        return this.keywordRepository.findById(id).orElse(null);
    }

    @Override
    public List<Keyword> findAllByResultMonth(int year, int month) {
        List<Keyword> keywordList = this.findAllKeywords();
        return keywordList
                .stream()
                .filter(keyword -> keyword
                        .getResults()
                        .stream()
                        .anyMatch(result -> result.getTime().getMonthValue() == month && result.getTime().getYear() == year))
                .collect(Collectors.toList());
    }
}
