package org.persimmon.book.controller;


import org.persimmon.book.model.Book;
import org.persimmon.book.model.BookCover;
import org.persimmon.book.model.Chapter;
import org.persimmon.book.service.BookService;
import org.persimmon.book.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.util.Random;

@Controller
public class ChapterController {

    Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @Autowired
    BookService bookService;

    @Autowired
    ChapterService chapterService;

    // 跳转到章节添加页面th:href="@{chapter}" method="get"
    @GetMapping("/chapter/{bookid}")
    public ModelAndView toAddChapterView(@PathVariable("bookid")Long bookID,
                                         Model model) {
        logger.info("添加图书章节");

        Book book = bookService.getBookByID(bookID);

        model.addAttribute("book", book);

        logger.info("通过 传进来的 Book ID 去找到图书，再对章节进行添加" + book);

        ModelAndView view = new ModelAndView();
        view.setViewName("addChapter");
        return view;
    }
    // 添加章节
    @PostMapping("/chapter/{bookid}")
    public ModelAndView addChapter(Chapter chapter , @PathVariable("bookid")Long bookID,@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("章节添加页面的表单提交 ");
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\Pc-Lc\\Desktop\\LearnSpring\\PersimmonBook\\src\\main\\resources\\static\\chapter\\";
        // 命名规则  bookID + _ + 第几章 + _ + fileName
        Book book = bookService.getBookByID(bookID);
//        book.addChapter()
        filePath = filePath + bookID + "_" + fileName;

        logger.info("最终计算的章节文本文件存放路径是： "  + filePath);
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        file.transferTo(dest);

        // 设置 Book 封面  通过ID 联系
        Chapter chapter1 = chapter;
        chapter1.setBelongBookID(bookID);
        chapter1.setChapterFilePath(filePath);

        logger.info("chapter  : " + chapter1 );

        chapterService.save(chapter1);

        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/bookPreview/{bookid}");
        return view;
    }

    // 图书章节展示，预览
    @GetMapping("/bookPreview/{id}")
    public ModelAndView bookPreview(@PathVariable("id")Long bookID,
                                    Model model) {
        Book book = bookService.getBookByID(bookID);
        model.addAttribute("book",book);

        model.addAttribute("chapters", chapterService.getAllChapter());
        ModelAndView view = new ModelAndView();

        view.setViewName("chapterList");
        return view;
    }


    @GetMapping("/bookread/{chapterID}")
    public ModelAndView read(@PathVariable("chapterID")Long chapterID, Model model) throws IOException {
        Chapter chapter = chapterService.getChapter(chapterID);
        model.addAttribute("chapter",chapter);
        String filePath = chapter.getChapterFilePath();
        String chapterTXT = null;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        while(bufferedReader.readLine() != null) {
            chapterTXT = chapterTXT + bufferedReader.readLine();
        }

        model.addAttribute("chapterTXT",chapterTXT);
        ModelAndView view = new ModelAndView();
        view.setViewName("chapterRead");
        return view;
    }

}
