package com.wronggo.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbBrandMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbBrand;
import com.wronggo.model.TbBrandExample;
import com.wrongo.service.TbBrandService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (TbBrand)表服务实现类
 *
 * @author makejava
 * @since 2020-10-16 10:58:29
 */
@Service
public class TbBrandServiceImpl implements TbBrandService {

    @Resource
    private TbBrandMapper tbBrandDao;

    @Override
    public List<TbBrand> selectall() {
        return this.tbBrandDao.selectall();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page=   (Page<TbBrand>) tbBrandDao.selectall();
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    public void add(TbBrand brand) {
        tbBrandDao.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandDao.selectbyid(id);
    }

    @Override
    public void update(TbBrand brand) {
        tbBrandDao.update(brand);
    }

    @Override
    public void delete(Long [] ids) {
        for(Long id : ids){
            tbBrandDao.delete(id);
        }

    }

    @Override
    public PageResult findPage1(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<TbBrand> page= (Page<TbBrand>)tbBrandDao.selectbylike(brand);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<TbBrand> selectbylike(TbBrand tbBrand) {
        return tbBrandDao.selectbylike(tbBrand);
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandDao.selectOptionList();
    }


}