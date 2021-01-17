package com.wronggo.mapper;

import com.wronggo.model.TbGoods;
import com.wronggo.model.goods;

import java.util.List;

public interface TbGoodsMapper {
    int insertSelective(TbGoods tbGoods);
    List<TbGoods> selectall();
    List<TbGoods>   selectbylike(TbGoods goods);
    TbGoods selectbyid(long id);
    int   updateByPrimaryKey(TbGoods tbGoods);
}
