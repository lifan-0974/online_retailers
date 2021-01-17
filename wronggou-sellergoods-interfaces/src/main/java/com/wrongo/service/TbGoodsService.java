package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbGoods;
import com.wronggo.model.goods;

import java.util.List;

public interface TbGoodsService {
    public void add(goods goods);
    public void update(goods goods);

    public PageResult findPage( int pageNum, int pageSize);
    public goods   findOne(Long id);
    public void updateStatus(Long []ids,String status);
    public void  delete(Long[] ids);
}
