package org.persimmon.book.model;

public class BookCover {
    private String coverName;
    private Integer coverUUid;
    private String belongBookname;
    private String belongBookID;

    public BookCover() {
    }

    public BookCover(Integer coverUUid) {
        this.coverUUid = coverUUid;
    }

    @Override
    public String toString() {
        return "BookCover{" +
                "coverName='" + coverName + '\'' +
                ", coverUUid='" + coverUUid + '\'' +
                ", belongBookname='" + belongBookname + '\'' +
                ", belongBookID='" + belongBookID + '\'' +
                '}';
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public Integer getCoverUUid() {
        return coverUUid;
    }

    public void setCoverUUid(Integer coverUUid) {
        this.coverUUid = coverUUid;
    }

    public String getBelongBookname() {
        return belongBookname;
    }

    public void setBelongBookname(String belongBookname) {
        this.belongBookname = belongBookname;
    }

    public String getBelongBookID() {
        return belongBookID;
    }

    public void setBelongBookID(String belongBookID) {
        this.belongBookID = belongBookID;
    }
}
