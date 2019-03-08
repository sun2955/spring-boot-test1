package com.sun.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/test")
public class ShiroController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "shiro you!");
        return "shiro";
    }
}
