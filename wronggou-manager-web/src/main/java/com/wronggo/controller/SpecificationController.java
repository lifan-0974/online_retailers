package com.wronggo.controller;

import com.wronggo.model.*;
import com.wrongo.service.TbSpecificationOptionService;
import com.wrongo.service.TbSpecificationService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private TbSpecificationService tbSpecificationService;
    @Reference
   private TbSpecificationOptionService tbSpecificationOptionService;
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return tbSpecificationService.selectall();
    }


    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return tbSpecificationService.findPage(page, rows);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification){
        try {
            tbSpecificationService.insert(specification);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    @RequestMapping("/findOne")
    public Specification findOne(Long id){
        return tbSpecificationService.selectbyid(id);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Specification specification){
        try {
            tbSpecificationService.update(specification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            tbSpecificationService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return tbSpecificationService.selectOptionList();
    }
}
