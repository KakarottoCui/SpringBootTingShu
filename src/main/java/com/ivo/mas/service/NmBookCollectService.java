package com.ivo.mas.service;

import com.ivo.mas.mapper.NmBookCollectMapper;
import com.ivo.mas.pojo.NmBookCollect;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class NmBookCollectService {

    @Resource
    private NmBookCollectMapper nmBookCollectMapper;
    
    /**
     * 查询多条数据
     *
     * @param nmBookCollect 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryNmBookCollectList(NmBookCollect nmBookCollect) {
        QueryWrapper<NmBookCollect> queryWrapper = new QueryWrapper<NmBookCollect>(nmBookCollect);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookCollectMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param nmBookCollect 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryNmBookCollectObject(NmBookCollect nmBookCollect) {
        QueryWrapper<NmBookCollect> queryWrapper = new QueryWrapper<NmBookCollect>(nmBookCollect);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookCollectMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param nmBookCollect 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmBookCollect(NmBookCollect nmBookCollect) {
        Long uid = SessionUtils.getUserId();
        nmBookCollect.setId(null);
        nmBookCollect.setValidFlag(1l);
        nmBookCollect.setCreateTime(new Date());
        nmBookCollect.setUpdateTime(new Date());
        nmBookCollect.setCreater(uid);
        nmBookCollect.setUpdater(uid);
        nmBookCollectMapper.insert(nmBookCollect);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmBookCollect);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmBookCollect 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmBookCollect(NmBookCollect nmBookCollect) {
        Long uid = SessionUtils.getUserId();
        boolean del = false;
        if(nmBookCollect.getValidFlag()==0l){
            del = true;
        }
        nmBookCollect.setValidFlag(1l);
        QueryWrapper<NmBookCollect> queryWrapper = new QueryWrapper<NmBookCollect>(nmBookCollect);
        NmBookCollect his = nmBookCollectMapper.selectOne(queryWrapper);
        if(his!=null){
            if(del){
                his.setValidFlag(0l);
                nmBookCollectMapper.updateById(his);
            }
        }else{
            nmBookCollect.setUpdateTime(new Date());
            nmBookCollect.setUpdater(uid);
            nmBookCollect.setCreateTime(new Date());
            nmBookCollect.setCreater(uid);
            nmBookCollectMapper.insert(nmBookCollect);
        }
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"操作成功",nmBookCollect);
    }

}
