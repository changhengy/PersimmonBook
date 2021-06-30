package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.persimmon.book.model.Book;

import java.util.Collection;

@Mapper
public interface BookMapper {

    @Select("select * from book where bookID=#{id}")
    public Book getBookByID(Integer id);

    @Select("select * from book")
    public Collection<Book> getAllBook();
}
