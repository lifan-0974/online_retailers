package com.wronggo.mapper;

import com.wronggo.model.TbContent;
import com.wronggo.model.TbContentExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbContentMapper {
    List<TbContent> selectByExample(TbContentExample example);
    List<TbContent> selectall();
    int insert(TbContent tbContent);
    int update(TbContent tbContent);
    int delete(Long id);
    TbContent selectByPrimaryKey(Long id);
}
