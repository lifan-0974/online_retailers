package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.TbContent;
import com.wronggo.model.goods;
import com.wronggo.service.ContentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("content")
public class ContentController {
    @Reference
    ContentService contentService;
    @RequestMapping("/add")
    public Result add(@RequestBody TbContent content){

        try {
            contentService.add(content);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){

        return contentService.findPage(page, rows);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbContent content){

        try {
            contentService.update(content);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
    @RequestMapping("/findOne")
    public TbContent findOne(Long id){
        return contentService.selectByPrimaryKey(id);
    }
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            contentService.delete(ids);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }
}
