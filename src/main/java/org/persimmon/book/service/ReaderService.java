package org.persimmon.book.service;

import org.persimmon.book.mapper.ReaderMapper;
import org.persimmon.book.model.Reader;
import org.persimmon.book.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    @Autowired
    ReaderMapper readerMapper;

    // 登录校验操作，判断用户是否存在，密码是否正确
    public Result readerLogin(Reader reader) {
        Result result = new Result(false, null);
        try {
            if (null != readerMapper.login(reader)) {
                System.out.println("登陆成功？ 用户名是： " + readerMapper.login(reader));
                result.setSuccess(true);
            } else {
                result.setMsg("用户名或密码错误，请重新登陆 I_AM_A_TEAPOT，登录失败，需要重新登录或者注册");
            }
        } catch (Exception exception) {
            result.setMsg(exception.getMessage());
        }

        return result;
    }

    // 注册 Reader
    public Result registReader(Reader reader) {
        Result result = new Result(false, null);
        // 先判断是否已经存在，再进行注册

        try {
            Reader existReader = readerMapper.findReaderByName(reader.getReadername());
            if (existReader != null) {
                System.out.println("用户已经存在，不能重复注册");
                result.setSuccess(false);
                result.setMsg("用户名已经存在，不能重复注册");
            } else {
                System.out.println("注册成功");
                readerMapper.insetReader(reader);
                result.setSuccess(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        }

        return result;
    }
}
