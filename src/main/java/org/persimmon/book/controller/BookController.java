package org.persimmon.book.controller;

import org.persimmon.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    //| 查询所有图书                      |   books   |  GET   |
    //  查询所有图书 就是返回booklist页面
    @GetMapping("/books")
    public ModelAndView list(Model model) {
        logger.info("查询所有图书 ");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        model.addAttribute("books", bookService.getAllBook());
        return modelAndView;
    }
    //| ---------------------------------- | :------: | :----: |
    //| 查询某个员工(来到修改页面操作)     | emp/{id} |  GET   |
    // 检索机能需要单独对应

    //| 点击添加按钮先来到添加页面             |   book    |  GET   |

    //| 添加图书                           |   book    |  POST  |


    //| 来到修改页面(查出图书进行信息回显) | book/{id} |  GET   |
    //  修改

    //| 修改图书信息                       |   book    |  PUT   |
    //  图书修改

    //| 删除图书                           | book/{id} | DELETE |
    //  删除请求


}
