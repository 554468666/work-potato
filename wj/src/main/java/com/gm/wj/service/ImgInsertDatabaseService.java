package com.gm.wj.service;

import com.gm.wj.dao.CategoryDAO;
import com.gm.wj.entity.Book;
import com.gm.wj.mapper.ImgInsertDatabaseMapper;
import com.gm.wj.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgInsertDatabaseService {
    @Autowired
    ImgInsertDatabaseMapper imgInsertDatabaseMapper;
    public Result imgInsertDatabase(Book book
    ){

        if(imgInsertDatabaseMapper.insert(book)>0){
            return new Result("00","插入成功","");
        }else {
            return new Result("99","插入失败","");
        }
    }

}
