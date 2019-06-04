package com.wxm.exc4.controller;

import com.wxm.exc4.annotation.AutoJump;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Registrar;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/05/13
 */
@RequestMapping("/")
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @AutoJump
    @RequestMapping(value = {"","login","index"},method = RequestMethod.GET)
    public String login(Model model,HttpSession session){
        return "login";
//        return login(model,session,1,"admin","registrar");

    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model,HttpSession session,@RequestParam("id")long id, @RequestParam("pwd") String pwd, @RequestParam("type") String type){
        Object o=null;
        switch (type){
            case "student":
                o=loginService.stuLogin(id,pwd);
                break;
            case "professor":
                o=loginService.profLogin(id,pwd);
                break;
            case "registrar":
                o=loginService.regLogin(id,pwd);
                break;
            default:break;
        }
        if(o instanceof Student){
            Student student=(Student) o;
            session.setAttribute("user",student);
            model.addAttribute("message",student);
            return "redirect:/student";
        }else if(o instanceof Professor){
            Professor professor= (Professor) o;
            session.setAttribute("user",professor);
            model.addAttribute("message",professor);
            return "redirect:/professor";
        }else if(o instanceof Registrar){
            Registrar registrar= (Registrar) o;
            session.setAttribute("user",registrar);
            model.addAttribute("message",registrar);
            return "redirect:/registrar";
        }else {
            model.addAttribute("tempId",id);
            model.addAttribute("message","登录失败,请检查用户名和密码");
            model.addAttribute("tempType",type);
            return "login";
        }
    }
}
