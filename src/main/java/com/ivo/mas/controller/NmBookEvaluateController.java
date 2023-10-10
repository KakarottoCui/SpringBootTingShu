package com.ivo.mas.controller;
import com.ivo.mas.service.NmBookEvaluateService;
import com.ivo.mas.pojo.NmBookEvaluate;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmBookEvaluate")
public class NmBookEvaluateController {

    @Resource
    NmBookEvaluateService nmBookEvaluateService;
    
    /**
     * 查询多条数据
     *
     * @param nmBookEvaluate 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmBookEvaluateList(@RequestBody NmBookEvaluate nmBookEvaluate){
        return nmBookEvaluateService.queryNmBookEvaluateList(nmBookEvaluate);
    }
    /**
     * 查询一条数据
     *
     * @param nmBookEvaluate 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmBookEvaluateObject(@RequestBody NmBookEvaluate nmBookEvaluate){
        return nmBookEvaluateService.queryNmBookEvaluateObject(nmBookEvaluate);
    }
    /**
     * 新增一条数据
     *
     * @param nmBookEvaluate 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmBookEvaluate")
    @ResponseBody
    public ResponseResult<Object> addNmBookEvaluate(@RequestBody NmBookEvaluate nmBookEvaluate){
        return nmBookEvaluateService.addNmBookEvaluate(nmBookEvaluate);
    }
    /**
     * 修改一条数据
     *
     * @param nmBookEvaluate 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmBookEvaluate")
    @ResponseBody
    public ResponseResult<Object> editNmBookEvaluate(@RequestBody NmBookEvaluate nmBookEvaluate){
        return nmBookEvaluateService.editNmBookEvaluate(nmBookEvaluate);
    }
    
}
