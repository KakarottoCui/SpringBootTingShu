package com.ivo.mas.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivo.mas.mapper.NmBookChapterMapper;
import com.ivo.mas.mapper.NmBookCollectMapper;
import com.ivo.mas.mapper.NmBookMapper;
import com.ivo.mas.mapper.SysUserMapper;
import com.ivo.mas.pojo.NmBook;

import com.ivo.mas.pojo.NmBookChapter;
import com.ivo.mas.pojo.NmBookCollect;
import com.ivo.mas.pojo.SysUser;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.SysInfo;
import com.ivo.mas.system.utils.CommonFunction;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.system.utils.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NmBookService {

    @Resource
    private NmBookMapper nmBookMapper;
    @Resource
    private NmBookCollectMapper nmBookCollectMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private NmBookChapterMapper nmBookChapterMapper;

    /**
     * 查询多条数据
     *
     * @param nmBook 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryNmBookList(NmBook nmBook) {
        String title = "";
        title = nmBook.getTitle();
        if(title!=null){
            nmBook.setTitle(null);
        }
        QueryWrapper<NmBook> queryWrapper = new QueryWrapper<NmBook>(nmBook);
        if(title!=null)
            queryWrapper.like("title",title);
        queryWrapper.orderByDesc("id");
        List<NmBook> nmBooks;
        if(nmBook.getPage()!=null){
            Integer limit = nmBook.getLimit() == null ? 10 : nmBook.getLimit();
            Page<NmBook> nmBooksPage = nmBookMapper.selectPage(new Page<NmBook>(nmBook.getPage(),limit),queryWrapper);
            nmBooks = nmBooksPage.getRecords();
        }else{
            nmBooks = nmBookMapper.selectList(queryWrapper);
        }
        for(NmBook oneBook:nmBooks){
            setCreaterInfo(oneBook);
        }
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmBooks);
    }
    
    /**
     * 查询一条数据
     *
     * @param nmBook 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryNmBookObject(NmBook nmBook) {
        QueryWrapper<NmBook> queryWrapper = new QueryWrapper<NmBook>(nmBook);
        NmBook resBook = nmBookMapper.selectOne(queryWrapper);
        setCollect(resBook);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",resBook);
    }
    
    /**
     * 新增一条数据
     *
     * @param nmBook 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmBook(NmBook nmBook) {
        Long uid = SessionUtils.getUserId();
        nmBook.setId(null);
        nmBook.setValidFlag(1l);
        nmBook.setCreateTime(new Date());
        nmBook.setUpdateTime(new Date());
        nmBook.setCreater(uid);
        nmBook.setUpdater(uid);
        setPic(nmBook);
        nmBookMapper.insert(nmBook);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmBook);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmBook 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmBook(NmBook nmBook) {
        Long uid = SessionUtils.getUserId();
        nmBook.setUpdateTime(new Date());
        nmBook.setUpdater(uid);
        setPic(nmBook);
        nmBookMapper.updateById(nmBook);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",nmBook);
    }

    public void setPic(NmBook nmBook){
        if(StringUtils.isNotEmpty(nmBook.getPic()) && nmBook.getPic().length()>100 ){
            String fileName = CommonFunction.SaveBase64Pic(nmBook.getPic());
            nmBook.setPic(fileName);
        }
        if(StringUtils.isNotEmpty(nmBook.getPic2()) && nmBook.getPic2().length()>100 ){
            String fileName = CommonFunction.SaveBase64Pic(nmBook.getPic2());
            nmBook.setPic2(fileName);
        }
    }

    public void setCreaterInfo(NmBook nmBook){
        SysUser sysUser = new SysUser();
        sysUser.setUid(nmBook.getCreater());
        QueryWrapper queryWrapper = new QueryWrapper(sysUser);
        sysUser = sysUserMapper.selectOne(queryWrapper);
        nmBook.setCName(sysUser.getNickName());

        NmBookCollect paramCollect = new NmBookCollect();
        paramCollect.setBid(nmBook.getId());
        paramCollect.setType(2l);
        paramCollect.setValidFlag(1l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allHis = nmBookCollectMapper.selectList(queryWrapper);
        nmBook.setHisNum(allHis.size());

        paramCollect.setType(1l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allCollect = nmBookCollectMapper.selectList(queryWrapper);
        nmBook.setCollectNum(allCollect.size());

    }

    public void setCollect(NmBook nmBook){
        NmBookCollect paramCollect = new NmBookCollect();
        paramCollect.setBid(nmBook.getId());
        paramCollect.setType(1l);
        paramCollect.setValidFlag(1l);
            QueryWrapper queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allCollect = nmBookCollectMapper.selectList(queryWrapper);
        nmBook.setCollectNum(allCollect.size());

        paramCollect.setType(2l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allHis = nmBookCollectMapper.selectList(queryWrapper);
        nmBook.setHisNum(allHis.size());

        SysUser sysUser = new SysUser();
        sysUser.setUid(nmBook.getCreater());
        queryWrapper = new QueryWrapper(sysUser);
        sysUser = sysUserMapper.selectOne(queryWrapper);
        nmBook.setCName(sysUser.getNickName());

        NmBookChapter chapter = new NmBookChapter();
        chapter.setValidFlag(1l);
        chapter.setBid(nmBook.getId());
        queryWrapper = new QueryWrapper(chapter);
        List<NmBookChapter> allChapter = nmBookChapterMapper.selectList(queryWrapper);
        nmBook.setChapterNum(allChapter.size());
    }

}
