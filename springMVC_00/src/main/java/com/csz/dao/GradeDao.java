package com.csz.dao;

import com.csz.model.Grade;

import java.util.List;

public interface GradeDao {
    List<Grade> SelectGrade();
    List<Grade> SelectTop3();
    List<Grade> SelectTopN(int top);
}
