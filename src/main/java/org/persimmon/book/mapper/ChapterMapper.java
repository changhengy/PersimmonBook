package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.*;
import org.persimmon.book.model.Book;
import org.persimmon.book.model.Chapter;

import java.util.Collection;

@Mapper
public interface ChapterMapper {
    @Insert("insert into chapter(chapterName,belongBookName,belongBookID,chapterFilePath,chapterIndex)" +
            "values(#{chapterName}, #{belongBookName}, #{belongBookID},#{chapterFilePath},#{chapterIndex})")
    void save(Chapter chapter1);

    @Delete("delete from chapter where chapterID=#{chapterID}")
    public void deleteChapter(Long chapterID);

    @Select("select * from chapter where chapterID=#{chapterID}")
    public Chapter getChapterByID(Long chapterID);

    @Select("select * from chapter")
    public Collection<Chapter> getAllChapter();
}
