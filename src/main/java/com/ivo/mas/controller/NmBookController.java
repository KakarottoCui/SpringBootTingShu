package com.ivo.mas.controller;
import com.ivo.mas.service.NmBookService;
import com.ivo.mas.pojo.NmBook;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmBook")
public class NmBookController {

    @Resource
    NmBookService nmBookService;

    /**
     * 查询多条数据
     *
     * @param nmBook 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmBookList(@RequestBody NmBook nmBook){
        return nmBookService.queryNmBookList(nmBook);
    }
    /**
     * 查询一条数据
     *
     * @param nmBook 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmBookObject(@RequestBody NmBook nmBook){
        return nmBookService.queryNmBookObject(nmBook);
    }
    /**
     * 新增一条数据
     *
     * @param nmBook 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmBook")
    @ResponseBody
    public ResponseResult<Object> addNmBook(@RequestBody NmBook nmBook){
        return nmBookService.addNmBook(nmBook);
    }
    /**
     * 修改一条数据
     *
     * @param nmBook 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmBook")
    @ResponseBody
    public ResponseResult<Object> editNmBook(@RequestBody NmBook nmBook){
        return nmBookService.editNmBook(nmBook);
    }
    
}
