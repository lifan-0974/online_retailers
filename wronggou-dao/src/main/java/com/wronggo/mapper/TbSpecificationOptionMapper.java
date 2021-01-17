package com.wronggo.mapper;

import java.util.List;

import com.wronggo.model.TbSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wronggo.model.TbSpecificationOption;
import com.wronggo.model.TbSpecificationOptionExample;
@Mapper
public interface TbSpecificationOptionMapper {

    List<TbSpecificationOption> selectByExample(TbSpecificationOptionExample example);
    int insert(TbSpecificationOption record);
   List<TbSpecificationOption> selectall(Long id);
   int deleteByExample(TbSpecificationOptionExample example);
}