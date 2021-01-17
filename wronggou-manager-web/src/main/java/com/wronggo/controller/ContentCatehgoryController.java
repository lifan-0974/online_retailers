package com.wronggo.controller;

import com.wronggo.model.TbBrand;
import com.wronggo.model.TbContentCategory;
import com.wronggo.service.ContentCatehgoryService;
import com.wronggo.util.R;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ContentCatehgory")
public class ContentCatehgoryController {

    @Reference
    ContentCatehgoryService contentCatehgoryService;

    @RequestMapping("selectall")
    public List<TbContentCategory> selectall() {
      return  contentCatehgoryService.selectall();
    }
}
