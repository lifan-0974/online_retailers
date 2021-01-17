package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.Specification;
import com.wronggo.model.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface bTypeTemplateService {
    List<TbTypeTemplate> selectall();
    public PageResult findPage(int pageNum, int pageSize);
    public void   insert(TbTypeTemplate tbTypeTemplate );
    public void  update(TbTypeTemplate tbTypeTemplate);
    TbTypeTemplate selectbyid(Long  id);
    public void  delete(Long [] ids);
    public List<Map> findSpecList(Long id);
}
