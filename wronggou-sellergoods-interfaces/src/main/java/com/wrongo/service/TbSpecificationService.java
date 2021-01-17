package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.Specification;
import com.wronggo.model.TbSpecification;

import java.util.List;
import java.util.Map;

public interface TbSpecificationService {

    List<TbSpecification> selectall();
    public PageResult findPage(int pageNum, int pageSize);
    public void   insert(Specification specification );
    public void  update(Specification record);
    Specification selectbyid(Long  id);
    public void  delete(Long [] ids);
    List<Map> selectOptionList();
}
