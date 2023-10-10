package com.ivo.mas.service;

import com.ivo.mas.mapper.NmBookGroupMapper;
import com.ivo.mas.pojo.NmBookGroup;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class NmBookGroupService {

    @Resource
    private NmBookGroupMapper nmBookGroupMapper;
    
    /**
     * 查询多条数据
     *
     * @param nmBookGroup 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryNmBookGroupList(NmBookGroup nmBookGroup) {
        QueryWrapper<NmBookGroup> queryWrapper = new QueryWrapper<NmBookGroup>(nmBookGroup);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookGroupMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param nmBookGroup 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryNmBookGroupObject(NmBookGroup nmBookGroup) {
        QueryWrapper<NmBookGroup> queryWrapper = new QueryWrapper<NmBookGroup>(nmBookGroup);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookGroupMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param nmBookGroup 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmBookGroup(NmBookGroup nmBookGroup) {
        Long uid = SessionUtils.getUserId();
        nmBookGroup.setId(null);
        nmBookGroup.setValidFlag(1l);
        nmBookGroup.setCreateTime(new Date());
        nmBookGroup.setUpdateTime(new Date());
        nmBookGroup.setCreater(uid);
        nmBookGroup.setUpdater(uid);
        nmBookGroupMapper.insert(nmBookGroup);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmBookGroup);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmBookGroup 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmBookGroup(NmBookGroup nmBookGroup) {
        Long uid = SessionUtils.getUserId();
        nmBookGroup.setUpdateTime(new Date());
        nmBookGroup.setUpdater(uid);
        nmBookGroupMapper.updateById(nmBookGroup);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",nmBookGroup);
    }

}
