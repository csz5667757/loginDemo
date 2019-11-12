package com.csz.controller;

import com.csz.common.dto.ResponseInfo;
import com.csz.model.User;
import com.csz.service.MenuService;
import com.csz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private MenuService menuService;

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
    @ResponseBody
    public Object selectAll(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
//        int userId = Integer.valueOf(request.getParameter("id"));
//        User user = this.userService.selectUser(userId);
        List<User> list = new ArrayList<User>();
        list = this.userService.selectAll();
        return ResponseInfo.Success(list);
    }

    @RequestMapping("/dengluUser")
    public String denglu(HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        session.setAttribute("logined","success");
        System.out.println(session);
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
            return "fail";
        }
        if (password.equals(user.getPassword())) {
            List lists = menuService.SelectAllMenu();
            List listSon = menuService.SelectSonMenu();
            model.addAttribute("menu",lists);
            model.addAttribute("son",listSon);
            return "layout";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/dengluGrade")
    public ModelAndView dengluGrade(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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
            mav.setViewName("grade");
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
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Date now = new Date();
        java.sql.Date use_Date = new java.sql.Date(now.getTime());
        String username = request.getParameter("username");
        //查询是否有该用户
        User existUser =userService.selectUserByusername(username);
        if (!(existUser==null)){
            return "userfail";
        }
        String password = request.getParameter("password");
        User user =new User();
        user.setAddress(address);
        user.setName(name);
        user.setPassword(password);
        user.setUse_date(use_Date);
        user.setUsername(username);
        Boolean b = userService.addUser(user);
        return "denglu";
    }

    @RequestMapping("/addUserTable")
    public String addUserTable(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Date now = new Date();
        java.sql.Date use_Date = new java.sql.Date(now.getTime());
        String username = request.getParameter("username");
        //查询是否有该用户
        User existUser =userService.selectUserByusername(username);
        if (!(existUser==null)){
            return "userfail";
        }
        String password = request.getParameter("password");
        User user =new User();
        user.setAddress(address);
        user.setName(name);
        user.setPassword(password);
        user.setUse_date(use_Date);
        user.setUsername(username);
        Boolean b = userService.addUser(user);
        return "/WEB-INF/views/user.ftl";
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

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Object deleteUserById(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println(request.getRequestURL());
        int id =Integer.valueOf(request.getParameter("id"));
        userService.deleteUserById(id);
        return ResponseInfo.Success("11");
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return "register";
    }

    @RequestMapping("/lookUser")
    public String lookUser(HttpServletRequest request,HttpServletResponse response,Model model) {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        List lists = menuService.SelectAllMenu();
        List listSon = menuService.SelectSonMenu();
        model.addAttribute("menu",lists);
        model.addAttribute("son",listSon);
        return "user";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("logined","logout");
        return "denglu";
    }

    @RequestMapping("/lookUserGrade")
    public String lookUserGrade(HttpServletRequest request,HttpServletResponse response,Model model) {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if (!(object=="success")){
            return "denglu";
        }
        List lists = menuService.SelectAllMenu();
        List listSon = menuService.SelectSonMenu();
        model.addAttribute("menu",lists);
        model.addAttribute("son",listSon);
        return "userGrade";
    }
}
