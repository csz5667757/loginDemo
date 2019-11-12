package com.csz.service;

import com.csz.model.Grade;

import java.util.List;

public interface GradeService {
    public List<Grade> SelectGrade();
    public List<Grade> SelectTop3();
    public List<Grade> SelectTopN(int top);

}
