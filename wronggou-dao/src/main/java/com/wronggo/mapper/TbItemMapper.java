package com.wronggo.mapper;

import com.wronggo.model.TbItem;
import com.wronggo.model.TbItemExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbItemMapper {
    List<TbItem> selectall();
    TbItem selectByPrimaryKey(Long id);
       int  insertSelective(TbItem tbItem);
    List<TbItem> selectByExample(TbItemExample example);
    int deleteByExample(TbItemExample example);
}
