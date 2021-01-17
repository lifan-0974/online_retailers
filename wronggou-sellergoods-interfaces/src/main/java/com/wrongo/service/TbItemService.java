package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbItem;

import java.util.List;

public interface TbItemService {
    List<TbItem> selectall();
    TbItem selectByPrimaryKey(Long id);
    public PageResult findPage(int pageNum, int pageSize);
}
