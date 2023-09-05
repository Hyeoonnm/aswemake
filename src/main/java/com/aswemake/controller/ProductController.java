package com.aswemake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public String list() {
        return "product/list";
    }

    @GetMapping("/add")
    public String add() {
        return "product/add";
    }
}
