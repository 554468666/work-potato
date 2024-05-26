package com.gm.wj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gm.wj.entity.Book;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ImgInsertDatabaseMapper extends BaseMapper<Book> {
    int insert(Book book);
}
