package org.persimmon.book.service;

import org.persimmon.book.mapper.BookMapper;
import org.persimmon.book.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    private static final List<String> bookTypes = new ArrayList<String>() {{
        add("修仙");
        add("都市");
        add("武侠");
        add("无限");
    }};

    @Autowired
    BookMapper bookMapper;

    public Collection<Book> getAllBook(){
        return bookMapper.getAllBook();
    }
    

    public void delete(Integer id) {
    }

    public void save(Book book) {
        logger.info("图书保存动作");
        book.setBookCreated(new Timestamp(1000));
        logger.info("book : "  + book.toString());
    }

    public Collection<String> getAllBookTypes() {
        return bookTypes;
    }

    public Book getBookByID(Integer bookID) {
        return bookMapper.getBookByID(bookID);
    }
}
