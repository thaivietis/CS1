package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.repository.ResultRepository;
import com.nqt.cs1.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImp implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result saveResult(Result result) {
        return this.resultRepository.save(result);
    }

    @Override
    public List<Result> getAllResult() {
        return this.resultRepository.findAll();
    }

    public String[] processSuggestions(Result result){
        return result.getSuggestions().split("\n\\s*");
    }


}
