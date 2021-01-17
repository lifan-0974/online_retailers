package com.wronggo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbContent;

import java.util.List;

public interface ContentService {
    public List<TbContent> findByCategoryId(Long categoryId);
    public void add(TbContent content);
    public void update(TbContent content);
    public void delete(Long[] ids);
    TbContent selectByPrimaryKey(Long id);
    public PageResult findPage(int pageNum, int pageSize);
}
