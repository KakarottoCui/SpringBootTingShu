package com.ivo.mas.controller;
import com.ivo.mas.service.NmBookChapterService;
import com.ivo.mas.pojo.NmBookChapter;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmBookChapter")
public class NmBookChapterController {

    @Resource
    NmBookChapterService nmBookChapterService;
    
    /**
     * 查询多条数据
     *
     * @param nmBookChapter 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmBookChapterList(@RequestBody NmBookChapter nmBookChapter){
        return nmBookChapterService.queryNmBookChapterList(nmBookChapter);
    }
    /**
     * 查询一条数据
     *
     * @param nmBookChapter 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmBookChapterObject(@RequestBody NmBookChapter nmBookChapter){
        return nmBookChapterService.queryNmBookChapterObject(nmBookChapter);
    }
    /**
     * 新增一条数据
     *
     * @param nmBookChapter 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmBookChapter")
    @ResponseBody
    public ResponseResult<Object> addNmBookChapter(@RequestBody NmBookChapter nmBookChapter){
        return nmBookChapterService.addNmBookChapter(nmBookChapter);
    }
    /**
     * 修改一条数据
     *
     * @param nmBookChapter 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmBookChapter")
    @ResponseBody
    public ResponseResult<Object> editNmBookChapter(@RequestBody NmBookChapter nmBookChapter){
        return nmBookChapterService.editNmBookChapter(nmBookChapter);
    }
    
}
