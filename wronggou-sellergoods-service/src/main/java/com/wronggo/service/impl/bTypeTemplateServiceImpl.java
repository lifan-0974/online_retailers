package com.wronggo.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbSpecificationOptionMapper;
import com.wronggo.mapper.TbTypeTemplateMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbSpecificationOption;
import com.wronggo.model.TbSpecificationOptionExample;
import com.wronggo.model.TbTypeTemplate;
import com.wrongo.service.bTypeTemplateService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class bTypeTemplateServiceImpl implements bTypeTemplateService {
    @Resource
    private  TbTypeTemplateMapper tbTypeTemplateMapper;
    @Resource
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<TbTypeTemplate> selectall() {
        return tbTypeTemplateMapper.selectall();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbTypeTemplate> page=   (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectall();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void insert(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.insert(tbTypeTemplate);
    }

    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.update(tbTypeTemplate);
    }

    @Override
    public TbTypeTemplate selectbyid(Long id) {

        return tbTypeTemplateMapper.selectbyid(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id :ids){
            tbTypeTemplateMapper.delete(id);
        }
    }

    @Override
    public List<Map> findSpecList(Long id) {
        TbTypeTemplate typeTemplate = tbTypeTemplateMapper.selectbyid(id);
        List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(),Map.class)  ;
        for(Map map:list){
            //查询规格选项列表
            TbSpecificationOptionExample example=new TbSpecificationOptionExample();
            com.wronggo.model.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo( new Long( (Integer)map.get("id") ) );
            List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
            map.put("options", options);
        }
        return list;
    }
}
