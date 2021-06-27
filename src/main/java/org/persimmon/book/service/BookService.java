package org.persimmon.book.service;

import org.persimmon.book.mapper.BookMapper;
import org.persimmon.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    public Collection<Book> getAllBook(){
        return bookMapper.getAllBook();
    }

//    public Book getBookByID(){
////        return bookMapper.
//    }

    @RequestMapping("/emp")
    public Book insert(Book book){
//        bookMapper.insertBook(book);
        return book;
    }
}
