package com.aswemake.controller;

import com.aswemake.entity.MemberEntity;
import com.aswemake.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final MemberService memberService;
    @GetMapping("/list")
    public String list(Model model, Authentication auth) {
        MemberEntity loginUser = memberService.getLoginUserByLoginId(auth.getName());
        model.addAttribute("user", loginUser);
        return "order/list";
    }
}
