package com.csz.controller;

import com.csz.model.User;
import com.csz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("请求的URL地址:" + requestUrl);
        int userId = Integer.valueOf(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
//        List<User> list = new ArrayList<User>();
//        list=this.userService.selectAll();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }

    @RequestMapping("/selectAll")
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        int userId = Integer.valueOf(request.getParameter("id"));
//        User user = this.userService.selectUser(userId);
        List<User> list = new ArrayList<User>();
        list = this.userService.selectAll();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(list));
        response.getWriter().close();
    }

    @RequestMapping("/denglu")
    public ModelAndView denglu(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = this.userService.selectUserByusername(username);
        List<User> list = new ArrayList<>();
        list = userService.selectAll();
        ModelAndView mav = new ModelAndView();

        if (user==null)
        {
            mav.setViewName("fail");
            return mav;
        }
        if (password.equals(user.getPassword())) {
            mav.addObject(list);
            mav.setViewName("success");
            return mav;
        } else {
            mav.setViewName("fail");
            return mav;
        }
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Date now = new Date();
        java.sql.Date use_Date = new java.sql.Date(now.getTime());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user =new User();
        user.setAddress(address);
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setUse_date(use_Date);
        user.setUsername(username);
        Boolean b = userService.addUser(user);
        return "success";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user =new User();
        user.setAddress(address);
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        userService.updateUserById(user);
        return  "success";
    }

    @RequestMapping("deleteUserById")
    public String deleteUserById(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id =Integer.valueOf(request.getParameter("id"));
        userService.deleteUserById(id);
        return "success";
    }
}
