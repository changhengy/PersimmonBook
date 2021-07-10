package org.persimmon.book.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.persimmon.book.mapper.ChapterMapper;
import org.persimmon.book.model.Book;
import org.persimmon.book.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    public void save(Chapter chapter1) {
        chapterMapper.save(chapter1);
    }

    public void deleteChapter(Long chapterID) {
        chapterMapper.deleteChapter(chapterID);
    }

    public Chapter getChapter(Long chapterID) {
        return chapterMapper.getChapterByID(chapterID);
    }

    public Collection<Chapter> getAllChapter() {
        return chapterMapper.getAllChapter();
    }

}
