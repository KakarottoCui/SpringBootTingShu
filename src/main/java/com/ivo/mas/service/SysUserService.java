package com.ivo.mas.service;

import com.ivo.mas.mapper.NmBookCollectMapper;
import com.ivo.mas.mapper.SysUserMapper;
import com.ivo.mas.pojo.NmBook;
import com.ivo.mas.pojo.NmBookCollect;
import com.ivo.mas.pojo.SysUser;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private NmBookCollectMapper nmBookCollectMapper;
    
    /**
     * 查询多条数据
     *
     * @param sysUser 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> querySysUserList(SysUser sysUser) {
        String nickName = "";
        nickName = sysUser.getNickName();
        if(nickName!=null){
            sysUser.setNickName(null);
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>(sysUser);
        if(nickName!=null)
            queryWrapper.like("nick_name",nickName);
        queryWrapper.orderByDesc("uid");
        List<SysUser> sysUserList = sysUserMapper.selectList(queryWrapper);
        for(SysUser user:sysUserList){
            NmBookCollect paramCollect = new NmBookCollect();
            paramCollect.setCreater(user.getUid());
            paramCollect.setType(2l);
            paramCollect.setValidFlag(1l);
            QueryWrapper queryWrapper2 = new QueryWrapper(paramCollect);
            List<NmBookCollect> allCollect = nmBookCollectMapper.selectList(queryWrapper2);
            user.setAge(allCollect.size());
        }
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserList);
    }
    
    /**
     * 查询一条数据
     *
     * @param sysUser 查询条件
     * @return 对象
     */
    public ResponseResult<Object> querySysUserObject(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param sysUser 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addSysUser(SysUser sysUser) {
        Long uid = SessionUtils.getUserId();
        sysUser.setUid(null);
        sysUser.setValidFlag(1l);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setCreater(uid);
        sysUser.setUpdater(uid);
        sysUserMapper.insert(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",sysUser);
    }
    
    /**
     * 修改一条数据
     *
     * @param sysUser 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editSysUser(SysUser sysUser) {
        Long uid = SessionUtils.getUserId();
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdater(uid);
        sysUserMapper.updateById(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",sysUser);
    }

}
