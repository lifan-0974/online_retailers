package com.wrongo.service;


import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;

import java.util.List;
import java.util.Map;

public interface TbBrandService {

    List<TbBrand> selectall();
    public PageResult findPage(int pageNum, int pageSize);
    public void add(TbBrand brand);
    public TbBrand findOne(Long id);
    public void update(TbBrand brand);
    public void delete(Long [] ids);
    public PageResult findPage1(TbBrand brand, int pageNum,int pageSize);
    List<TbBrand> selectbylike(TbBrand tbBrand);
    List<Map> selectOptionList();
}
