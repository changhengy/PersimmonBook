package org.persimmon.book.controller;
/*
 * @time 2021/6/23 12:15
 * @author chy
 */

import org.persimmon.book.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class LoginController {
    @GetMapping("/")
    public ModelAndView toLoginPager(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @PostMapping("/reader")
    public ModelAndView login(Reader reader){
        ModelAndView view = new ModelAndView();
        System.out.println("reader  +++++ " + reader);
        // 如果已经存在则，且校验密码,欧克  后转向dashboard
        //
        // 不存在，跳转到注册界面
        view.setViewName("login");
        return view;
    }
}
