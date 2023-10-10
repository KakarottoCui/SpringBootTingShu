package com.ivo.mas.service;

import com.ivo.mas.mapper.NmBookEvaluateMapper;
import com.ivo.mas.pojo.NmBookEvaluate;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class NmBookEvaluateService {

    @Resource
    private NmBookEvaluateMapper nmBookEvaluateMapper;
    
    /**
     * 查询多条数据
     *
     * @param nmBookEvaluate 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryNmBookEvaluateList(NmBookEvaluate nmBookEvaluate) {
        QueryWrapper<NmBookEvaluate> queryWrapper = new QueryWrapper<NmBookEvaluate>(nmBookEvaluate);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookEvaluateMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param nmBookEvaluate 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryNmBookEvaluateObject(NmBookEvaluate nmBookEvaluate) {
        QueryWrapper<NmBookEvaluate> queryWrapper = new QueryWrapper<NmBookEvaluate>(nmBookEvaluate);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookEvaluateMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param nmBookEvaluate 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmBookEvaluate(NmBookEvaluate nmBookEvaluate) {
        Long uid = SessionUtils.getUserId();
        nmBookEvaluate.setId(null);
        nmBookEvaluate.setValidFlag(1l);
        nmBookEvaluate.setCreateTime(new Date());
        nmBookEvaluate.setUpdateTime(new Date());
        nmBookEvaluate.setCreater(uid);
        nmBookEvaluate.setUpdater(uid);
        nmBookEvaluateMapper.insert(nmBookEvaluate);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmBookEvaluate);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmBookEvaluate 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmBookEvaluate(NmBookEvaluate nmBookEvaluate) {
        Long uid = SessionUtils.getUserId();
        nmBookEvaluate.setUpdateTime(new Date());
        nmBookEvaluate.setUpdater(uid);
        nmBookEvaluateMapper.updateById(nmBookEvaluate);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",nmBookEvaluate);
    }

}
