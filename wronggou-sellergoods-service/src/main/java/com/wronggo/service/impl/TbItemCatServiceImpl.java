package com.wronggo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbItemCatMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbItem;
import com.wronggo.model.TbItemCat;
import com.wrongo.service.TbItemCatService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Resource
    TbItemCatMapper tbItemCatMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public List<TbItemCat> selectall() {
        return tbItemCatMapper.selectall();
    }

    @Override
    public List<TbItemCat> selectByPrimaryKey(long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page=   (Page<TbItemCat>) tbItemCatMapper.selectall();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TbItemCat selectByid(long id) {
        return tbItemCatMapper.selectByid(id);
    }

    @Override
    public int insertSelective(TbItemCat tbItemCat) {
        return tbItemCatMapper.insertSelective(tbItemCat);
    }

    @Override
    public int updateByPrimaryKey(TbItemCat tbItemCat) {
        return tbItemCatMapper.updateByPrimaryKey(tbItemCat);
    }

    @Override
    public void deleteByPrimaryKey(Long [] ids) {
       for (Long id : ids)
         tbItemCatMapper.deleteByPrimaryKey(id);
    }
}
