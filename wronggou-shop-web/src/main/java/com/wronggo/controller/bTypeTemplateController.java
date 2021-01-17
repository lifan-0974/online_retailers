package com.wronggo.controller;

import com.wronggo.model.PageResult;
import com.wronggo.model.Result;
import com.wronggo.model.TbTypeTemplate;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bTypeTemplate")
public class bTypeTemplateController {
    @Reference
    private com.wrongo.service.bTypeTemplateService bTypeTemplateService;

    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return bTypeTemplateService.findPage(page, rows);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbTypeTemplate tbTypeTemplate){
        try {
            bTypeTemplateService.insert(tbTypeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id){
        return bTypeTemplateService.selectbyid(id);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbTypeTemplate tbTypeTemplate){
        try {
            bTypeTemplateService.update(tbTypeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            bTypeTemplateService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/findSpecList")
    public List<Map> findSpecList(Long id){
        return bTypeTemplateService.findSpecList(id);
    }
}
