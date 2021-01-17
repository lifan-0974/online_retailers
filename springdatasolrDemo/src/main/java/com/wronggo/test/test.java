package com.wronggo.test;

import com.alibaba.fastjson.JSONObject;
import com.wronggo.model.TbItem;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class test {

 private HttpSolrClient solrClient=new HttpSolrClient.Builder("http://127.0.0.1:8090/solr").build();
 //添加
    @Test
    public void testAdd() throws IOException, SolrServerException {
        TbItem item=new TbItem();
        item.setId("1");
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(null);
        UpdateResponse collection1 = solrClient.addBean("collection1", item);
        solrClient.commit("collection1");
        int status = collection1.getStatus();
   System.out.print("状态"+status);
    }
    //修改
    @Test
    public void testUpdate() throws IOException, SolrServerException {
        TbItem item=new TbItem();
        item.setId("536563");
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(null);
        UpdateResponse collection1 = solrClient.addBean("collection1", item);
        solrClient.commit("collection1");
        int status = collection1.getStatus();
        System.out.print("状态"+status);
    }
    //删除
    @Test
    public void testDelete() throws IOException, SolrServerException {

        UpdateResponse collection1 = solrClient.deleteById("collection1", "536563");
        solrClient.commit("collection1");
        int status = collection1.getStatus();
        System.out.print("状态"+status);
    }
  //  根据id查询
    @Test
    public void testselectByid() throws IOException, SolrServerException {

        SolrDocument SolrDocument = solrClient.getById("collection1", "562379");
        DocumentObjectBinder binder = solrClient.getBinder();
        TbItem tbItem = binder.getBean(TbItem.class, SolrDocument);
        String s = JSONObject.toJSONString(tbItem);
        System.out.print(s);

    }
    //根据条件查询
    @Test
    public void testselectByname() throws IOException, SolrServerException {

        SolrQuery solrQuery=new SolrQuery();
        solrQuery.set("q","item_category:手机");
        solrQuery.set("start","0");
        solrQuery.set("rows","3");
        QueryResponse queryResponse=solrClient.query("collection1",solrQuery);
        List<TbItem> beans = queryResponse.getBeans(TbItem.class);
        for (TbItem tbItem:beans){
            String jsonString = JSONObject.toJSONString(tbItem);
            System.out.print(jsonString);
        }

    }
}
