package com.aswemake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class RootController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

}
