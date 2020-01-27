package com.milo.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","index","/index"})
    public String getIndexPage(){
        return "index";
    }

}
