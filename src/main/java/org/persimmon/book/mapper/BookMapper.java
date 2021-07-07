package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.*;
import org.persimmon.book.model.Book;

import java.util.Collection;

@Mapper
public interface BookMapper {

    @Select("select * from book where bookID=#{id}")
    public Book getBookByID(Long id);

    @Select("select * from book")
    public Collection<Book> getAllBook();

    @Insert("insert into book(bookName,bookDescription,bookAuthorID,bookAuthorName,bookCreated,bookUpdated,bookType,bookCoverID,bookCoverName)" +
            "values(#{bookName}, #{bookDescription}, #{bookAuthorID},#{bookAuthorName},#{bookCreated},#{bookUpdated},#{bookType}, #{bookCoverID},#{bookCoverName})")
    public void saveBook(Book book);


    @Update("update book set bookName=#{bookName},bookDescription=#{bookDescription},bookAuthorID=#{bookAuthorID},bookAuthorName=#{bookAuthorName}," +
            "bookCreated=#{bookCreated},bookUpdated=#{bookUpdated},bookType=#{bookType} where bookID=#{bookID}")
    public void updateBook(Book book);

    @Delete("delete from book where bookID=#{bookID}")
    public void deleteBook(Long bookID);
}
