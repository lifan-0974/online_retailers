package com.wronggo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbItemMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbItem;
import com.wrongo.service.TbItemService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Resource
    TbItemMapper tbItemMapper;
    @Override
    public List<TbItem> selectall() {
        return tbItemMapper.selectall();
    }

    @Override
    public TbItem selectByPrimaryKey(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItem> page=   (Page<TbItem>) tbItemMapper.selectall();
        return new PageResult(page.getTotal(), page.getResult());
    }
}
