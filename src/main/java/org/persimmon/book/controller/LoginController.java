package org.persimmon.book.controller;
/*
 * @time 2021/6/23 12:15
 * @author chy
 */

import org.persimmon.book.model.Reader;
import org.persimmon.book.model.Result;
import org.persimmon.book.service.BookService;
import org.persimmon.book.service.ReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    ReaderService readerService;

    @Autowired
    BookService bookService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public ModelAndView toLoginPager(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @PostMapping("/reader/login")
    public ModelAndView login(Reader reader, HttpSession session, Model model){
        ModelAndView view = new ModelAndView();
        System.out.println("reader  +++++ " + reader);
        Result result = readerService.readerLogin(reader);
        if (result.isSuccess()) {
            logger.info("登录成功");
            // session 设置内容，方便登录 拦截器配置
            session.setAttribute("loginUser", reader.getReadername());
            model.addAttribute("books", bookService.getAllBook());
            model.addAttribute("bookname", "测试bookname 能不能取得");
            view.setViewName("redirect:/main.html");
//            view.setViewName("cover");

        } else {
            // 登录失败
            logger.info("登录失败");
            view.setStatus(HttpStatus.I_AM_A_TEAPOT);
            view.addObject("msg",result.getMsg());
            view.setViewName("login");
        }

        return view;
    }

    @PostMapping("/reader/regist")
    public ModelAndView regist(Reader reader) {
        // 注册成功应该往哪里跳转？欢迎页面吗？  welcome 一下？
        logger.info("注册读者 方法被调用， "  + reader);
        ModelAndView view = new ModelAndView();
        Result result = readerService.registReader(reader);
        if (result.isSuccess()) {
//            view.setViewName("cover");
            view.setViewName("redirect:/main.html");
        } else {
            view.addObject("msg",result.getMsg());
            view.setViewName("regist");
        }
        return view;
    }

    @GetMapping("/reader/toRegister")
    public ModelAndView toRegister(){
        ModelAndView view = new ModelAndView();
        view.setViewName("regist");
        return view;
    }
}
