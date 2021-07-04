package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.persimmon.book.model.Book;

import java.util.Collection;

@Mapper
public interface BookMapper {

    @Select("select * from book where bookID=#{id}")
    public Book getBookByID(Long id);

    @Select("select * from book")
    public Collection<Book> getAllBook();

    @Insert("insert into book(bookName,bookDescription,bookAuthorID,bookAuthorName,bookCreated,bookUpdated,bookType)" +
            "values(#{bookName}, #{bookDescription}, #{bookAuthorID},#{bookAuthorName},#{bookCreated},#{bookUpdated},#{bookType})")
    public void saveBook(Book book);


    @Delete("delete from book where bookID=#{bookID}")
    public void deleteBook(Long bookID);
}
