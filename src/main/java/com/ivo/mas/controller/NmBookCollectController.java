package com.ivo.mas.controller;
import com.ivo.mas.service.NmBookCollectService;
import com.ivo.mas.pojo.NmBookCollect;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmBookCollect")
public class NmBookCollectController {

    @Resource
    NmBookCollectService nmBookCollectService;
    
    /**
     * 查询多条数据
     *
     * @param nmBookCollect 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmBookCollectList(@RequestBody NmBookCollect nmBookCollect){
        return nmBookCollectService.queryNmBookCollectList(nmBookCollect);
    }
    /**
     * 查询一条数据
     *
     * @param nmBookCollect 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmBookCollectObject(@RequestBody NmBookCollect nmBookCollect){
        return nmBookCollectService.queryNmBookCollectObject(nmBookCollect);
    }
    /**
     * 新增一条数据
     *
     * @param nmBookCollect 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmBookCollect")
    @ResponseBody
    public ResponseResult<Object> addNmBookCollect(@RequestBody NmBookCollect nmBookCollect){
        return nmBookCollectService.addNmBookCollect(nmBookCollect);
    }
    /**
     * 修改一条数据
     *
     * @param nmBookCollect 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmBookCollect")
    @ResponseBody
    public ResponseResult<Object> editNmBookCollect(@RequestBody NmBookCollect nmBookCollect){
        return nmBookCollectService.editNmBookCollect(nmBookCollect);
    }
    
}
