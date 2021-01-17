package com.wronggo.mapper;

import java.util.List;
import java.util.Map;


import com.wronggo.model.Specification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wronggo.model.TbSpecification;
import com.wronggo.model.TbSpecificationExample;
@Mapper
public interface TbSpecificationMapper {


    List<TbSpecification> selectall();
    int insert(TbSpecification specification );

    int update(TbSpecification record);
    TbSpecification  selectbyid(Long  id);
    int delete(Long  ids);

    List<Map> selectOptionList();
}