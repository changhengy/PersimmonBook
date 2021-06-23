package org.persimmon.book.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.persimmon.book.model.Reader;

@Mapper
public interface ReaderMapper {
    // 判断当前Reader 是否已经存在数据库中

    // 插入数据
    @Insert("insert into reader(readername, password) values (#{readername}, #{password})")
    public void insetReader(Reader reader);
}
