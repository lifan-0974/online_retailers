package com.wronggo.mapper;

import com.wronggo.model.TbContentCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbContentCategoryMapper {
    List<TbContentCategory> selectall();
}
