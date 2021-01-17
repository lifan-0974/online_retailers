package com.wrongo.service;

import com.wronggo.model.PageResult;
import com.wronggo.model.TbSeller;

import java.util.List;

public interface TbSellerService {
    int insert(TbSeller tbSeller );
    public void updateStatus(String sellerId,String status);
    public PageResult findPage(int pageNum, int pageSize);
    TbSeller selectbyname(String name);
}
