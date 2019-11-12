package com.csz.controller;

import com.csz.common.dto.ResponseInfo;
import com.csz.model.Menu;
import com.csz.service.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping("/lookMenu")
    public String LookMenu(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if(!(object=="success")){
            return "denglu";
        }
        List list = menuService.SelectAllMenu();
        List listSon = menuService.SelectSonMenu();
        model.addAttribute("menu",list);
        model.addAttribute("son",listSon);
        return "menu";
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectAllMenu(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object object = session.getAttribute("logined");
        if(!(object=="success")){
            return "denglu";
        }
        List list = menuService.SelectAllMenu();
        return ResponseInfo.Success(list);
    }

    @RequestMapping("/ftlTest")
    public Object ftlTest(Model model) throws JsonProcessingException {
        List list = menuService.SelectAllMenu();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(list));
        model.addAttribute("test",list);
        return "textss";
    }

    @RequestMapping("/AddMenu")
    public Object addMenu(HttpServletRequest request, HttpServletResponse response,Model model){
        String menu_type =request.getParameter("menu_type");
        String menu_name =request.getParameter("menu_name");
        String url =request.getParameter("url");
        String pid =request.getParameter("pid");
        Menu menu = new Menu();
        menu.setEnabled(true);
        menu.setMenu_name(menu_name);
        menu.setMenu_type(Integer.valueOf(menu_type));
        if (!(pid=="")&&!pid.isEmpty()){
            menu.setPid(Integer.valueOf(pid));
        }
        menu.setUrl(url);
        Boolean b = menuService.AddMenu(menu);
        if (b==true){
            List list = menuService.SelectAllMenu();
            List listSon = menuService.SelectSonMenu();
            model.addAttribute("menu",list);
            model.addAttribute("son",listSon);
        return "menu";}
        else{
            return null;
        }
    }

    @RequestMapping("/deleteMenuById")
    public Object deleteMenuById(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Boolean b = menuService.deleteMenuById(id);
        if (b==true){
            List list = menuService.SelectAllMenu();
            List listSon = menuService.SelectSonMenu();
            model.addAttribute("menu",list);
            model.addAttribute("son",listSon);
            return "menu";
        }
        else{
            return "null";
        }
    }

    @RequestMapping("/UpdateMenu")
    public Object UpdateMenuById(HttpServletRequest request,Model model){
        int id = Integer.valueOf(request.getParameter("id"));
        int pid = Integer.valueOf(request.getParameter("pid"));
        String menu_name = request.getParameter("menu_name");
        int menu_type = Integer.valueOf(request.getParameter("menu_type"));
        String url = request.getParameter("url");
        Menu menu = new Menu();
        menu.setId(id);
        menu.setUrl(url);
        menu.setPid(pid);
        menu.setMenu_type(menu_type);
        menu.setMenu_name(menu_name);
        Boolean b = menuService.UpdateMenuById(menu);
        if (b==true){
            List list = menuService.SelectAllMenu();
            List listSon = menuService.SelectSonMenu();
            model.addAttribute("menu",list);
            model.addAttribute("son",listSon);
            return "menu";
        }
        else{
            return "null";
        }
    }
}
