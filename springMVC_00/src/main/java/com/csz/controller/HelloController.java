package com.csz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        System.out.println("hello=================");
        return "indexs.html";
    }

    @RequestMapping("web")
    public String web(){
        return "web";
    }
}
