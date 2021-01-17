package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbItemCat;

import java.util.List;

public interface TbItemCatService {
    List<TbItemCat> selectall();
    List<TbItemCat> selectByPrimaryKey(long id);
    public PageResult findPage(int pageNum, int pageSize);
    TbItemCat selectByid(long id);
    int insertSelective(TbItemCat tbItemCat);
    int updateByPrimaryKey(TbItemCat tbItemCat);

    public void deleteByPrimaryKey(Long [] ids);
}
