package com.wronggo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbSpecificationMapper;
import com.wronggo.mapper.TbSpecificationOptionMapper;
import com.wronggo.model.*;
import com.wrongo.service.TbSpecificationService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TbSpecificationServiceImpl implements TbSpecificationService {

    @Resource
    private TbSpecificationMapper tbSpecificationMapper;
    @Resource
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;
    @Override
    public List<TbSpecification> selectall() {
        return tbSpecificationMapper.selectall();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page=   (Page<TbSpecification>) tbSpecificationMapper.selectall();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void insert(Specification specification) {
        tbSpecificationMapper.insert(specification.getSpecification());//插入规格
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){	specificationOption.setSpecId(specification.getSpecification().getId());
            tbSpecificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void update(Specification specification) {
//保存修改的规格
        tbSpecificationMapper.update(specification.getSpecification());//保存规格
        //删除原有的规格选项
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        com.wronggo.model.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
        tbSpecificationOptionMapper.deleteByExample(example);//删除
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            tbSpecificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public Specification selectbyid(Long id) {
        TbSpecification selectbyid = tbSpecificationMapper.selectbyid(id);
        //查询规格选项列表
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        com.wronggo.model.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = tbSpecificationOptionMapper.selectall(id);
        //构建组合实体类返回结果
        Specification spec=new Specification();
        spec.setSpecification(selectbyid);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            tbSpecificationMapper.delete(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example=new TbSpecificationOptionExample();
            com.wronggo.model.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定规格ID为条件
            tbSpecificationOptionMapper.deleteByExample(example);//删除
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return tbSpecificationMapper.selectOptionList();
    }


}
