package com.aswemake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }


    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
}