package org.persimmon.book.service;

import org.persimmon.book.mapper.ReaderMapper;
import org.persimmon.book.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    @Autowired
    ReaderMapper readerMapper;

    // 登录校验操作，判断用户是否存在，
    public boolean readerLogin(Reader reader) {
        return false;
    }

    public boolean registReader(Reader reader) {
        readerMapper.insetReader(reader);
        return true;
    }
}
