package com.nqt.cs1.service;

import com.nqt.cs1.domain.Result;

import java.util.List;

public interface ResultService {
    Result saveResult(Result result);
    List<Result> getAllResult();
}
