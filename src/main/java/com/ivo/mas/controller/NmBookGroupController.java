package com.ivo.mas.controller;
import com.ivo.mas.service.NmBookGroupService;
import com.ivo.mas.pojo.NmBookGroup;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmBookGroup")
public class NmBookGroupController {

    @Resource
    NmBookGroupService nmBookGroupService;
    
    /**
     * 查询多条数据
     *
     * @param nmBookGroup 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmBookGroupList(@RequestBody NmBookGroup nmBookGroup){
        return nmBookGroupService.queryNmBookGroupList(nmBookGroup);
    }
    /**
     * 查询一条数据
     *
     * @param nmBookGroup 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmBookGroupObject(@RequestBody NmBookGroup nmBookGroup){
        return nmBookGroupService.queryNmBookGroupObject(nmBookGroup);
    }
    /**
     * 新增一条数据
     *
     * @param nmBookGroup 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmBookGroup")
    @ResponseBody
    public ResponseResult<Object> addNmBookGroup(@RequestBody NmBookGroup nmBookGroup){
        return nmBookGroupService.addNmBookGroup(nmBookGroup);
    }
    /**
     * 修改一条数据
     *
     * @param nmBookGroup 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmBookGroup")
    @ResponseBody
    public ResponseResult<Object> editNmBookGroup(@RequestBody NmBookGroup nmBookGroup){
        return nmBookGroupService.editNmBookGroup(nmBookGroup);
    }
    
}
