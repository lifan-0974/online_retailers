package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.TbItemCat;
import com.wrongo.service.TbItemCatService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("TbItemCat")
public class TbItemCatController {
    @Reference
    TbItemCatService tbItemCatService;
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return tbItemCatService.findPage(page, rows);
    }
    @RequestMapping("/findall")
    public List<TbItemCat> findall(){
        return tbItemCatService.selectall();
    }
    @RequestMapping("/findOne")
    public List<TbItemCat> findOne(Long parentId){
        return tbItemCatService.selectByPrimaryKey(parentId);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbItemCat tbItemCat){
        try {
            tbItemCatService.insertSelective(tbItemCat);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbItemCat tbItemCat){
        try {
            tbItemCatService.updateByPrimaryKey(tbItemCat);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/findOne1")
    public TbItemCat findOne1(Long id){
        return tbItemCatService.selectByid(id);
    }
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            tbItemCatService.deleteByPrimaryKey(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
