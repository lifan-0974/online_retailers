package com.wronggo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wronggo.model.*;
import com.wrongo.service.TbGoodsService;
import com.wrongo.service.bTypeTemplateService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("Goods")
public class GoodsController {
    @Reference
    TbGoodsService tbGoodsService;

//    private goods processJsonRequest(JSONObject json) {
//
//        goods goods = new goods();
//
//
//        JSONObject goodsJson = json.getJSONObject("goods");
//
//        TbGoods tbGoods = new TbGoods();
//
//        tbGoods.setCategory1Id(goodsJson.getLong("category1Id"));
//        tbGoods.setCategory2Id(goodsJson.getLong("category3Id"));
//        tbGoods.setCategory3Id(goodsJson.getLong("category3Id"));
//        tbGoods.setTypeTemplateId(goodsJson.getLong("type TemplateId"));
//        tbGoods.setGoodsName(goodsJson.getString("goodsName "));
//
//        tbGoods.setBrandId(goodsJson.getLong("brandId"));
//
//        tbGoods.setCaption(goodsJson.getString(" caption"));
//        tbGoods.setPrice(goodsJson.getBigDecimal("Price"));
//
//
//        goods.setGoods(tbGoods);
//
//        JSONObject goodsDescJSON = json.getJSONObject("goodsDesc");
//
//        TbGoodsDesc tbGoodsDesc = new TbGoodsDesc();
//
//        tbGoodsDesc.setItemImages(goodsDescJSON.getString("itemImages"));
//
//        tbGoodsDesc.setSpecificationItems(goodsDescJSON.getString("specificationItems"));
//        tbGoodsDesc.setIntroduction(goodsDescJSON.getString(" introduction"));
//
//        tbGoodsDesc.setCustomAttributeItems(goodsDescJSON.getString("cus tomAttributeItems"));
//        tbGoodsDesc.setPackageList(goodsDescJSON.getString("packageList"));
//
//        tbGoodsDesc.setSaleService(goodsDescJSON.getString("saleService"));
//
//        goods.setGoodsDesc(tbGoodsDesc);
//
//        JSONArray itemList = json. getJSONArray( "itemList" );
//
//        ArrayList<TbItem> tbItems = new ArrayList<>();
//
//        for (int i = 0; i < itemList.size(); i++) {
//
//            JSONObject json0bject = itemList.getJSONObject(i);
//            TbItem tbitems=new TbItem();
//
//
//                    tbitems.setSpec(json0bject.getString("spec"));
//
//                    tbitems.setPrice(json0bject.getBigDecimal("price"));
//
//                    tbitems.setNum(json0bject.getInteger("num" ));
//
//                    tbitems .setStatus(json0bject.getString("status "));
//
//                    tbitems .setIsDefault(json0bject.getString(" isDefault" ));
//                    tbitems .setTitle("默认标题");
//
//                    tbitems .setCategoryid(tbGoods.getCategory3Id());
//
//                    tbitems .setStatus("1");
//
//                    tbitems .setCreateTime(new Date());
//
//                     tbitems .setUpdateTime(new Date());
//
//        }
//            System.out.println("Json-»»" + tbItems.toString());
//            goods.setItemList (tbItems);
//
//              return goods ;
//    }
@RequestMapping("/add")
public Result add(@RequestBody goods goods){
    //获取登录名
    String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
    goods.getGoods().setSellerId(sellerId);//设置商家ID
    try {
        tbGoodsService.add(goods);
        return new Result(true, "增加成功");
    } catch (Exception e) {
        e.printStackTrace();
        return new Result(false, "增加失败");
    }
}

    @RequestMapping("/findPage")
    public PageResult findPage( int page, int rows){

        return tbGoodsService.findPage(page, rows);
    }
    @RequestMapping("/findOne")
    public goods findOne(Long id){
        return tbGoodsService.findOne(id);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody goods goods){
        //校验是否是当前商家的id
        goods goods2 = tbGoodsService.findOne(goods.getGoods().getId());
        //获取当前登录的商家ID
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        //如果传递过来的商家ID并不是当前登录的用户的ID,则属于非法操作
        if(!goods2.getGoods().getSellerId().equals(sellerId) ||  !goods.getGoods().getSellerId().equals(sellerId) ){
            return new Result(false, "操作非法");
        }
        try {
            tbGoodsService.update(goods);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
}
