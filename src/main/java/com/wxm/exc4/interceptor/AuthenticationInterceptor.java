package com.wxm.exc4.interceptor;


import com.wxm.exc4.annotation.AdminLoginRequire;
import com.wxm.exc4.annotation.AutoJump;
import com.wxm.exc4.annotation.StudentLoginRequire;
import com.wxm.exc4.annotation.TeacherLoginRequire;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Registrar;
import com.wxm.exc4.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)||!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        Object user=request.getSession().getAttribute("user");
        //检查是否有passtoken注释，有则跳过认证
        //if (method.isAnnotationPresent(PassToken.class)) {
        //    PassToken passToken = method.getAnnotation(PassToken.class);
        //    if (passToken.required()) {
        //        return true;
        //    }
        //}
        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(LoginRequire.class)) {
//            LoginRequire loginRequire = method.getAnnotation(LoginRequire.class);
//            if (loginRequire.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new BaseApiException(ResponseCodeEnum.NO_TOKEN);
//                }
//                if(verifyService.verify(token)){
//
//                }else {
//                    return false;
//                }
//
//            }
//        }
        if(method.isAnnotationPresent(AutoJump.class)){
//            System.out.println("autojump");
            if(user instanceof Student){
                try {
                    response.sendRedirect("/student");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }else if(user instanceof Professor){
                try {
                    response.sendRedirect("/professor");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }else if(user instanceof Registrar){
                try {
                    response.sendRedirect("/registrar");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(method.isAnnotationPresent(StudentLoginRequire.class)){//如果有注解
            if(!(user instanceof Student)){
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }else if(method.isAnnotationPresent(TeacherLoginRequire.class)){
            if(!(user instanceof Professor)){
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }else if(method.isAnnotationPresent(AdminLoginRequire.class)){
            if(!(user instanceof Registrar)){
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
