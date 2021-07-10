package org.persimmon.book.model;

//CREATE TABLE chapter(
//        chapterID serial PRIMARY KEY,
//        chapterName TEXT,
//        belongBookName integer,
//        belongBookID integer,
//        chapterFilePath TEXT,
//        chapterIndex integer
//        );

public class Chapter {
    private Long chapterID;
    private String chapterName;
    private String belongBookName;
    private Long belongBookID;

    private String chapterFilePath;
    // 属于本书的第几章？
    private Integer chapterIndex;

    public Long getChapterID() {
        return chapterID;
    }

    public void setChapterID(Long chapterID) {
        this.chapterID = chapterID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getBelongBookName() {
        return belongBookName;
    }

    public void setBelongBookName(String belongBookName) {
        this.belongBookName = belongBookName;
    }

    public Long getBelongBookID() {
        return belongBookID;
    }

    public void setBelongBookID(Long belongBookID) {
        this.belongBookID = belongBookID;
    }

    public String getChapterFilePath() {
        return chapterFilePath;
    }

    public void setChapterFilePath(String chapterFilePath) {
        this.chapterFilePath = chapterFilePath;
    }

    public Integer getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(Integer chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterID=" + chapterID +
                ", chapterName='" + chapterName + '\'' +
                ", belongBookName='" + belongBookName + '\'' +
                ", belongBookID=" + belongBookID +
                ", chapterFilePath='" + chapterFilePath + '\'' +
                ", chapterIndex=" + chapterIndex +
                '}';
    }
}
