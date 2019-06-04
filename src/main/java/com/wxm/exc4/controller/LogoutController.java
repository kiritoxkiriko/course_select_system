package com.wxm.exc4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/05/14
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping("")
    public String logout(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/login";
    }
}
