package com.wronggo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.*;
import com.wronggo.model.*;
import com.wrongo.service.TbGoodsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TbGoodsServiceImpl implements TbGoodsService {
    @Resource
    TbGoodsMapper tbGoodsMapper;
    @Resource
    TbGoodsDescMapper tbGoodsDescMapper;
    @Resource
    private TbItemMapper itemMapper;
    @Resource
    private TbBrandMapper brandMapper;
    @Resource
    private TbItemCatMapper itemCatMapper;
    @Resource
    private TbSellerMapper sellerMapper;
    @Override
    public void add(goods goodss) {
        goodss.getGoods().setAuditStatus("0");//设置未申请状态
        tbGoodsMapper.insertSelective(goodss.getGoods());
        goodss.getGoodsDesc().setGoodsId(goodss.getGoods().getId());//设置ID
        tbGoodsDescMapper.insertSelective(goodss.getGoodsDesc());//插入商品扩展数据

    
    }

        @Override
    public void update(goods goods) {
        goods.getGoods().setAuditStatus("0");//设置未申请状态:如果是经过修改的商品，需要重新设置状态
        tbGoodsMapper.updateByPrimaryKey(goods.getGoods());//保存商品表
        tbGoodsDescMapper.updateByPrimaryKey(goods.getGoodsDesc());//保存商品扩展表
        //删除原有的sku列表数据
        TbItemExample example=new TbItemExample();
        com.wronggo.model.TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goods.getGoods().getId());
        itemMapper.deleteByExample(example);
            if("1".equals(goods.getGoods().getIsEnableSpec())){
                for(TbItem item :goods.getItemList()){
                    //标题
                    String title= goods.getGoods().getGoodsName();
                    Map<String,Object> specMap = JSON.parseObject(item.getSpec());
                    for(String key:specMap.keySet()){
                        title+=" "+ specMap.get(key);
                    }
                    item.setTitle(title);
                    setItemValus(goods,item);
                    itemMapper.insertSelective(item);
                }
            }else{
                TbItem item=new TbItem();
                item.setTitle(goods.getGoods().getGoodsName());//商品KPU+规格描述串作为SKU名称
                item.setPrice( goods.getGoods().getPrice() );//价格
                item.setStatus("1");//状态
                item.setIsDefault("1");//是否默认
                item.setNum(99999);//库存数量
                item.setSpec("{}");
                setItemValus(goods,item);
                itemMapper.insertSelective(item);
            }
    }

    @Override
    public PageResult findPage( int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<TbGoods> page= (Page<TbGoods>)tbGoodsMapper.selectall();

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public goods findOne(Long id) {
        goods goods=new goods();
        TbGoods tbGoods = tbGoodsMapper.selectbyid(id);
        goods.setGoods(tbGoods);
        TbGoodsDesc tbGoodsDesc = tbGoodsDescMapper.selectbyid(id);
        goods.setGoodsDesc(tbGoodsDesc);
        TbItemExample example=new TbItemExample();
        com.wronggo.model.TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(id);//查询条件：商品ID
        List<TbItem> itemList = itemMapper.selectByExample(example);
        goods.setItemList(itemList);
        return goods;
    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        for(Long id:ids){
            TbGoods goods = tbGoodsMapper.selectbyid(id);
            goods.setAuditStatus(status);
            tbGoodsMapper.updateByPrimaryKey(goods);
        }
    }
    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            TbGoods goods = tbGoodsMapper.selectbyid(id);
            goods.setIsDelete("1");
            tbGoodsMapper.updateByPrimaryKey(goods);
        }
    }

    private void setItemValus(goods goods,TbItem item) {
        item.setGoodsId(goods.getGoods().getId());//商品SPU编号
        item.setSellerId(goods.getGoods().getSellerId());//商家编号
        item.setCategoryid(goods.getGoods().getCategory3Id());//商品分类编号（3级）
        item.setCreateTime(new Date());//创建日期
        item.setUpdateTime(new Date());//修改日期

        //品牌名称
        TbBrand brand = brandMapper.selectbyid(goods.getGoods().getBrandId());
        item.setBrand(brand.getName());
        //分类名称
        TbItemCat itemCat = itemCatMapper.selectByid(goods.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());

        //商家名称
        TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
        item.setSeller(seller.getNickName());

        //图片地址（取spu的第一个图片）
        List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class) ;
        if(imageList.size()>0){
            item.setImage ( (String)imageList.get(0).get("url"));
        }
    }
    }

