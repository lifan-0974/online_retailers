package com.wronggo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbBrandExample;

import java.util.List;
import java.util.Map;

@Mapper
public interface TbBrandMapper {
   List<TbBrand> selectall();
   int insert(TbBrand record);
   List<Map> selectOptionList();
   int update(TbBrand record);
   TbBrand selectbyid(Long  id);
   int delete(Long  ids);
   List<TbBrand>  selectByExample(TbBrandExample example);
   List<TbBrand> selectbylike(TbBrand tbBrand);
}