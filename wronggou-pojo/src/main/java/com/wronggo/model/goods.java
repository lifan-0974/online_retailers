package com.wronggo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class goods implements Serializable {
    private TbGoods goods;//商品SPU
    private TbGoodsDesc goodsDesc;//商品扩展
    private List<TbItem> itemList;//商品SKU列表

}
