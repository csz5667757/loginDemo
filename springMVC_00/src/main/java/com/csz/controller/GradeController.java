package com.csz.controller;

import com.csz.common.dto.ResponseInfo;
import com.csz.model.Grade;
import com.csz.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/grade")
    @ResponseBody
    public Object SelectGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        List<Grade> list = new ArrayList<Grade>();
        list = gradeService.SelectGrade();
        return ResponseInfo.Success(list);
    }

    @RequestMapping("/topThreegrade")
    public void SelectTops(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        Object object = session.getAttribute("logined");
//        if (!(object=="success")){
//            return "denglu";
//        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        List<Grade> list = new ArrayList<Grade>();
        list = gradeService.SelectTop3();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(list));
        response.getWriter().close();
    }

    @RequestMapping("/topNgrade")
    @ResponseBody
    public Object SelectTopN(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        String top = request.getParameter("top");
        if (top==null||top==""){
            top="10";
        }
        List list = gradeService.SelectTopN(Integer.valueOf(top));
        return ResponseInfo.Success(list);
    }
}
