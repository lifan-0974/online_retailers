package com.wronggo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbSellerMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbSeller;
import com.wrongo.service.TbSellerService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TbSellerServiceImpl implements TbSellerService {
    @Resource
    TbSellerMapper tbSellerMapper;
    @Override
    public int insert(TbSeller tbSeller) {
        tbSeller.setStatus("0");
        tbSeller.setCreateTime(new Date());
       return tbSellerMapper.insert(tbSeller);
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        TbSeller seller = tbSellerMapper.selectbyname(sellerId);
        seller.setStatus(status);
        tbSellerMapper.updateByPrimaryKey(seller);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSeller> page=   (Page<TbSeller>) tbSellerMapper.selectall();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TbSeller selectbyname(String name) {
        return tbSellerMapper.selectbyname(name);
    }


}
