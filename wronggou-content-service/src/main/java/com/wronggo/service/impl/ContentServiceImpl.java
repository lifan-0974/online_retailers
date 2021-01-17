package com.wronggo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wronggo.mapper.TbContentMapper;
import com.wronggo.model.PageResult;
import com.wronggo.model.TbContent;
import com.wronggo.model.TbContentExample;
import com.wronggo.model.TbGoods;
import com.wronggo.service.ContentService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    TbContentMapper tbContentMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        List<TbContent> contentList= (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
        if(contentList==null){
        TbContentExample contentExample=new TbContentExample();
        TbContentExample.Criteria criteria2 = contentExample.createCriteria();
        criteria2.andCategoryIdEqualTo(categoryId);
        criteria2.andStatusEqualTo("1");//开启状态
        contentExample.setOrderByClause("sort_order");//排序
          contentList =  tbContentMapper.selectByExample(contentExample);
         redisTemplate.boundHashOps("content").put(categoryId,contentList);
        }else{
            System.out.println("从缓存读取数据");
        }
        return  contentList;
    }

    @Override
    public void add(TbContent content) {
        tbContentMapper.insert(content);
        //清除缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    @Override
    public void update(TbContent content) {
        Long categoryId = tbContentMapper.selectByPrimaryKey(content.getId()).getCategoryId();
        redisTemplate.boundHashOps("content").delete(categoryId);
        tbContentMapper.update(content);
        //如果分类ID发生了修改,清除修改后的分类ID的缓存
        if (categoryId.longValue() != content.getCategoryId().longValue()) {
            if (categoryId.longValue() != content.getCategoryId().longValue()) {
                redisTemplate.boundHashOps("content").delete(content.getCategoryId());
            }
        }


    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            //清除缓存
            Long categoryId = tbContentMapper.selectByPrimaryKey(id).getCategoryId();//广告分类ID
            redisTemplate.boundHashOps("content").delete(categoryId);
            tbContentMapper.delete(id);
        }
    }

    @Override
    public TbContent selectByPrimaryKey(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<TbContent> page= (Page<TbContent>)tbContentMapper.selectall();

        return new PageResult(page.getTotal(), page.getResult());
    }

}
