package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.*;
import org.persimmon.book.model.Reader;

@Mapper
public interface ReaderMapper {
    // 判断当前Reader 是否已经存在数据库中，可以直接通过用户名和密码进行select 操作，能找到就说明OK，找不到就说明用户名或者密码错误


    /**
     * 登录
     * @param reader
     * @return
     */
    @Select("select r.readername from reader r where r.readername = #{readername} and password = #{password}")
    String login(Reader reader);

    // 注册用于判断用户师傅已经存在
    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param readername
     * @return
     */
    @Select(value = "select r.readername,r.readername reader reader r where r.readername=#{readername}")
    @Results({@Result(property = "readername",column = "username"),
              @Result(property = "password",column = "password")})
    Reader findUserByName(@Param("readername") String readername);


    @Select("selecat * from reader")
    public Reader findReaderByName(String name);
    // 插入数据
    @Insert("insert into reader(readername, password) values (#{readername}, #{password})")
    public boolean insetReader(Reader reader);
}
