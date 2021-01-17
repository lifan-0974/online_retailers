package com.wronggo.mapper;

import com.wronggo.model.TbSeller;
import com.wronggo.model.TbSpecification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbSellerMapper {
    int insert(TbSeller tbSeller );
   TbSeller selectbyname(String name);
   int updateByPrimaryKey(TbSeller tbSeller);
   List<TbSeller> selectall();

    TbSeller selectByPrimaryKey(String sellerId);
}
