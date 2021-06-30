package org.persimmon.book.service;

import org.persimmon.book.mapper.BookMapper;
import org.persimmon.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookService {

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
    }

    public Collection<String> getAllBookTypes() {
        return bookTypes;
    }

    public Book getBookByID(Integer bookID) {
        return bookMapper.getBookByID(bookID);
    }
}
