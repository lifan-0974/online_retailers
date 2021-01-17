package com.wronggo.service.impl;

import com.wronggo.mapper.TbSpecificationOptionMapper;
import com.wronggo.model.TbSpecificationOption;
import com.wrongo.service.TbSpecificationOptionService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class TbSpecificationOptionServiceImpl implements TbSpecificationOptionService {
    @Resource
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public int insert(TbSpecificationOption record) {
        return tbSpecificationOptionMapper.insert(record);
    }
}
