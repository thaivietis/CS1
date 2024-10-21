package com.nqt.cs1.service;

import com.nqt.cs1.domain.Keyword;

import java.util.List;

public interface KeywordService {
    Keyword saveKeyword(Keyword keyword);
    List<Keyword> findAllKeywords();
    Keyword findById(long id);
    List<Keyword> findAllByResultMonth (int resultYear, int month);
}
