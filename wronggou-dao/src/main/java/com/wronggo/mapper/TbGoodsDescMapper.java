package com.wronggo.mapper;

import com.wronggo.model.TbGoodsDesc;

public interface TbGoodsDescMapper {
    int insertSelective(TbGoodsDesc tbGoodsDesc);
    TbGoodsDesc selectbyid(Long id);
    int  updateByPrimaryKey(TbGoodsDesc tbGoodsDesc);
}
