package org.persimmon.book.model;

import java.sql.Timestamp;
import java.util.List;

//CREATE TABLE book(
//        bookID serial PRIMARY KEY,
//        bookName TEXT,
//        bookCoverID TEXT,
//        bookCoverName TEXT,
//        bookAuthorID TEXT,
//        bookAuthorName TEXT,
//        bookDescription TEXT,
//        bookCreated  timestamp(6),
//        bookUpdated timestamp(6),
//        bookType TEXT,
//        bookTags text[],
//        chapterIDList integer[]
//        );

public class Book {
    private Long bookID;
    private String bookName;
    private Long bookAuthorID;
    private String bookAuthorName;
    private String bookDescription; // 图书简介
    private Timestamp bookCreated;  // 创建时间
    private Timestamp bookUpdated;  // 最后更新时间
    private String bookType;        // 图书类型
    private List<String> bookTags;  // 图书标签
    private List<Long> chapterIDList;

    private Long bookCoverID;
    private String bookCoverName;

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", bookAuthorID=" + bookAuthorID +
                ", bookAuthorName='" + bookAuthorName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookCreated=" + bookCreated +
                ", bookUpdated=" + bookUpdated +
                ", bookType='" + bookType + '\'' +
                ", bookTags=" + bookTags +
                ", chapterIDList=" + chapterIDList +
                ", bookCoverID=" + bookCoverID +
                ", bookCoverName='" + bookCoverName + '\'' +
                '}';
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getBookCoverID() {
        return bookCoverID;
    }

    public void setBookCoverID(Long bookCoverID) {
        this.bookCoverID = bookCoverID;
    }

    public String getBookCoverName() {
        return bookCoverName;
    }

    public void setBookCoverName(String bookCoverName) {
        this.bookCoverName = bookCoverName;
    }

    public Long getBookAuthorID() {
        return bookAuthorID;
    }

    public void setBookAuthorID(Long bookAuthorID) {
        this.bookAuthorID = bookAuthorID;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Timestamp getBookCreated() {
        return bookCreated;
    }

    public void setBookCreated(Timestamp bookCreated) {
        this.bookCreated = bookCreated;
    }

    public Timestamp getBookUpdated() {
        return bookUpdated;
    }

    public void setBookUpdated(Timestamp bookUpdated) {
        this.bookUpdated = bookUpdated;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public List<String> getBookTags() {
        return bookTags;
    }

    public void setBookTags(List<String> bookTags) {
        this.bookTags = bookTags;
    }

    public List<Long> getChapterIDList() {
        return chapterIDList;
    }

    public void setChapterIDList(List<Long> chapterIDList) {
        this.chapterIDList = chapterIDList;
    }
}