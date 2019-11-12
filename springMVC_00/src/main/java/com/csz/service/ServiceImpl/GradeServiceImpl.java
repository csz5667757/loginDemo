package com.csz.service.ServiceImpl;

import com.csz.dao.GradeDao;
import com.csz.model.Grade;
import com.csz.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("GradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<Grade> SelectGrade() {
        List<Grade> list = new ArrayList<>();
        list = gradeDao.SelectGrade();
        return list;
    }

    @Override
    public List<Grade> SelectTop3() {
        return gradeDao.SelectTop3();
    }

    @Override
    public List<Grade> SelectTopN(int top) {
        return gradeDao.SelectTopN(top);
    }
}
