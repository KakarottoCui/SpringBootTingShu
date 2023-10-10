package com.ivo.mas.service;

import com.ivo.mas.mapper.NmBookChapterMapper;
import com.ivo.mas.pojo.NmBookChapter;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.b
     * @return 对象
     */
    public ResponseResult<Object> queryNmBookChapterObject(NmBookChapter nmBookChapter) {
        QueryWrapper<NmBookChapter> queryWrapper = new QueryWrapper<NmBookChapter>(nmBookChapter);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBookChapterMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param nmBookChapter 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmBookChapter(NmBookChapter nmBookChapter) {
        Long uid = SessionUtils.getUserId();
        nmBookChapter.setId(null);
        nmBookChapter.setValidFlag(1l);
        nmBookChapter.setCreateTime(new Date());
        nmBookChapter.setUpdateTime(new Date());
        nmBookChapter.setCreater(uid);
        nmBookChapter.setUpdater(uid);
        nmBookChapterMapper.insert(nmBookChapter);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmBookChapter);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmBookChapter 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmBookChapter(NmBookChapter nmBookChapter) {
        Long uid = SessionUtils.getUserId();
        nmBookChapter.setUpdateTime(new Date());
        nmBookChapter.setUpdater(uid);
        nmBookChapterMapper.updateById(nmBookChapter);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",nmBookChapter);
    }

}
