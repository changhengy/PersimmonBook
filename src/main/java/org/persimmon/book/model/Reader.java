package org.persimmon.book.model;
/*
 * @time 2021/6/23 12:03
 * @author chy
 * 读者类
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
//CREATE TABLE reader(
//        READERID serial PRIMARY KEY,
//        READERNAME TEXT,
//        PASSWORD TEXT,
//        EMAIL TEXT,
//        PHONE TEXT,
//        GENDER TEXT);

public class Reader {
    private Integer readerid; // 自增非空
    private String  readername; // 非空
    private String  password; // 非空
    private String  email;
    private String  phone;
    private String  gender;

    @Override
    public String toString() {
        return "Reader{" +
                "readerid=" + readerid +
                ", readername='" + readername + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getReaderid() {
        return readerid;
    }

    public void setReaderid(Integer readerid) {
        this.readerid = readerid;
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
//    private Date createDate; // 注册时间，计算柿子年龄
//

}
