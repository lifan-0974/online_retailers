package com.wronggo.mapper;

import java.util.List;

import com.wronggo.model.TbSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wronggo.model.TbTypeTemplate;
import com.wronggo.model.TbTypeTemplateExample;
@Mapper
public interface TbTypeTemplateMapper {


    List<TbTypeTemplate> selectall();
    int insert(TbTypeTemplate tbTypeTemplate );

    int update(TbTypeTemplate tbTypeTemplate);
    TbTypeTemplate  selectbyid(Long  id);
    int delete(Long  ids);
}