package com.nqt.cs1.service;

import com.nqt.cs1.domain.Keyword;

import java.util.List;
import java.util.Optional;

public interface KeywordService {
    Keyword saveKeyword(Keyword keyword);
    List<Keyword> findAllKeywords();
    Keyword findById(long id);
}
