package com.wronggo.service.impl;

import com.wronggo.mapper.TbContentCategoryMapper;
import com.wronggo.model.TbContentCategory;
import com.wronggo.service.ContentCatehgoryService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ContentCatehgoryServiceImpl implements ContentCatehgoryService {
    @Resource
    TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TbContentCategory> selectall() {
        return tbContentCategoryMapper.selectall();
    }
}
