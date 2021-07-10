package org.persimmon.book.controller;

import org.persimmon.book.model.Book;
import org.persimmon.book.model.BookCover;
import org.persimmon.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

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
    //| 查询某本图书(来到修改页面操作)     | book/{id} |  GET   |
    // 检索机能需要单独对应


    //| 点击添加按钮先来到添加页面             |   book    |  GET   |
    @GetMapping("/book")
    public ModelAndView toAddpage(Model model){
        logger.info("响应点击添加按钮，跳转添加页面");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addBook");

        model.addAttribute("booktypes", bookService.getAllBookTypes());
        return modelAndView;
    }
    @Value("${file.upload.path}")
    private String path;

    //| 添加图书                           |   book    |  POST  |
    @PostMapping("/book")
    public ModelAndView addBook(Book book , HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("图书添加页面的表单提交 ");
        logger.info("book  : " + book );

        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\Pc-Lc\\Desktop\\LearnSpring\\PersimmonBook\\src\\main\\resources\\static\\bookcover\\";

        // 设置 Book 封面  通过ID 联系
        BookCover cover = new BookCover(new Random().nextInt(100000));
        book.setBookCoverID(cover.getCoverUUid());
        book.setBookCoverName(cover.getCoverUUid() + fileName);

        filePath = filePath + cover.getCoverUUid() + fileName;

        logger.info("最终计算的图片存放路径是： "  + filePath);
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        file.transferTo(dest);

        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        book.setBookAuthorName(loginUser);

        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/books");
        return modelAndView;
    }

    //| 来到修改页面(查出图书进行信息回显) | book/{id} |  GET   |
    //  修改
    @GetMapping("/update/{id}")
    public ModelAndView updataBookPage(@PathVariable("id")Long bookID,
                                  Model model){

        Book book = bookService.getBookByID(bookID);
        model.addAttribute("book",book);

        //部门选择的修改
        Collection<String> booktypes = bookService.getAllBookTypes();
        model.addAttribute("booktypes", booktypes);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addBook");
        return modelAndView;
    }
    //| 修改图书信息                       |   book    |  PUT   |
    //  图书修改

    @PutMapping("/book")
    public String updataToEmp(Book book , @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("图书修改页面的表单提交 ");
        logger.info("book  : " + book );
        if (null == file) {
            logger.info("file  :  空文件 ，上传失败"  );
        }

        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        logger.info("最终计算的图片存放路径是： "  + filePath);
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        //修改的数据
        bookService.updateBook(book);
        return "redirect:/books";
    }
    //| 删除图书                           | book/{id} | DELETE |
    //  删除请求
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long bookID){
        bookService.delete(bookID);

        return "redirect:/books";
    }

}
