package com.wronggo.mapper;

import com.wronggo.model.TbItem;
import com.wronggo.model.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    List<TbItemCat> selectall();
    List<TbItemCat> selectByPrimaryKey(long id);
    TbItemCat selectByid(long id);
    int insertSelective(TbItemCat tbItemCat);
    int updateByPrimaryKey(TbItemCat tbItemCat);

    int deleteByPrimaryKey(long id);
}
