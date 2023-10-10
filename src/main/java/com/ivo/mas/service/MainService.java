package com.ivo.mas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.mapper.NmBookCollectMapper;
import com.ivo.mas.mapper.NmBookMapper;
import com.ivo.mas.mapper.SysUserLoginMapper;
import com.ivo.mas.mapper.SysUserMapper;
import com.ivo.mas.pojo.NmBook;
import com.ivo.mas.pojo.NmBookCollect;
import com.ivo.mas.pojo.SysUser;
import com.ivo.mas.pojo.SysUserLogin;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.SysInfo;
import com.ivo.mas.system.restTemp.RestTemp;
import com.ivo.mas.system.restful.WxRestful;
import com.ivo.mas.system.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class MainService {

    @Resource
    private SysUserMapper quUserMapper;
    @Resource
    private SysUserLoginMapper nmUserLoginMapper;
    @Resource
    private NmBookCollectMapper nmBookCollectMapper;
    @Resource
    private NmBookMapper nmBookMapper;


    public ResponseResult getLocationInfo(String location){
        RestTemp restTemp = new RestTemp();
        String res = restTemp.getForString("http://api.map.baidu.com/geocoder?location="+location+"&output=json");
        JSONObject resJsonObj = JSON.parseObject(res);
        resJsonObj = resJsonObj.getObject("result",JSONObject.class);
        return new ResponseResult(ResponseCode.SUCCESS, "位置获取成功",resJsonObj.get("addressComponent"));
    }

    public Boolean setLogin(String token, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        if (sysUser != null && token.equals(sysUser.getToken())) {
            return true;
        } else {
            SysUserLogin sysUserLogin = new SysUserLogin();
            sysUserLogin.setToken(token);
            sysUserLogin.setValidFlag(1l);
            QueryWrapper queryWrapper = new QueryWrapper(sysUserLogin);
            sysUserLogin = nmUserLoginMapper.selectOne(queryWrapper);
            if (sysUserLogin == null) {
                SessionUtils.removeSessionAttribute("user");
                return true;
            } else {
                sysUser = new SysUser();
                sysUser.setOpenId(sysUserLogin.getOpenId());
                sysUser.setValidFlag(1l);
                queryWrapper = new QueryWrapper(sysUser);
                sysUser = quUserMapper.selectOne(queryWrapper);
                if (sysUser != null) {
                    sysUser.setToken(token);
                    SessionUtils.addSessionAttribute("user", sysUser);
                    return true;
                } else {
                    sysUser = new SysUser();
                    sysUser.setValidFlag(1l);
                    sysUser.setUid(sysUserLogin.getUid());
                    sysUser.setOpenId(null);
                    queryWrapper = new QueryWrapper(sysUser);
                    sysUser = quUserMapper.selectOne(queryWrapper);
                    if (sysUser != null) {
                        sysUser.setToken(token);
                        SessionUtils.addSessionAttribute("user", sysUser);
                        return true;
                    }else{
                        SessionUtils.removeSessionAttribute("user");
                        return true;
                    }
                }
            }
        }
    }


    public ResponseResult getUserInfo(String code, String enData, String iv, String userInfo) {
        String sys = WxRestful.wxGetUserKey(code);
        HashMap<String, Object> res = new HashMap<String, Object>();
        Map<String, String> keyMap = JSON.parseObject(sys, HashMap.class);
        Map<String, String> userMap = JSON.parseObject(userInfo, HashMap.class);
        SysUser sysUser = new SysUser();
        sysUser.setOpenId(keyMap.get("openid"));
        sysUser.setValidFlag(1l);
        QueryWrapper queryWrapper = new QueryWrapper(sysUser);
        sysUser = quUserMapper.selectOne(queryWrapper);
        if (sysUser != null) {
            SysUserLogin sysUserLogin = new SysUserLogin();
            sysUserLogin.setValidFlag(1l);
            sysUserLogin.setOpenId(keyMap.get("openid"));
            queryWrapper = new QueryWrapper(sysUserLogin);
            queryWrapper.orderByDesc("create_time");
            sysUserLogin = nmUserLoginMapper.selectOne(queryWrapper);
            String token = "";
            if (sysUserLogin == null) {
                sysUserLogin = new SysUserLogin();
                sysUserLogin.setValidFlag(1l);
                sysUserLogin.setOpenId(keyMap.get("openid"));
                token = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
                sysUserLogin.setUid(sysUser.getUid());
                sysUserLogin.setToken(token);
                sysUserLogin.setCreateTime(new Date());
                sysUserLogin.setCreater(sysUser.getUid());
                sysUserLogin.setSessionKey(keyMap.get("session_key"));
                sysUserLogin.setUpdateTime(new Date());
                sysUserLogin.setUpdater(sysUser.getUid());
                nmUserLoginMapper.insert(sysUserLogin);
            } else {
                sysUserLogin.setUpdateTime(new Date());
                token = sysUserLogin.getToken();
                nmUserLoginMapper.updateById(sysUserLogin);
            }
            sysUser.setToken(token);
            return new ResponseResult(ResponseCode.SUCCESS, sysUser);

        } else {
            sysUser = new SysUser();
            sysUser.setOpenId(keyMap.get("openid"));
            sysUser.setValidFlag(1l);
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            sysUser.setNickName(userMap.get("nickName"));
            sysUser.setRealName("");
            sysUser.setOpenId(keyMap.get("openid"));
            sysUser.setPic(userMap.get("avatarUrl"));
            sysUser.setSessionKey("MTIzNDU2");
            sysUser.setMemo("0");
            quUserMapper.insert(sysUser);
            sysUser.setToken("");
            return new ResponseResult(ResponseCode.SUCCESS, sysUser);
        }
    }

    public ResponseResult regist(String data){
        SysUser sysUser = JSON.parseObject(data, SysUser.class);
        sysUser.setValidFlag(1l);
        SysUser param = new SysUser();
        param.setNickName(sysUser.getNickName());
        param.setValidFlag(1l);
        QueryWrapper queryWrapper = new QueryWrapper(param);
        param = quUserMapper.selectOne(queryWrapper);
        if(param!=null){
            return new ResponseResult(ResponseCode.SERVICE_ERROR, "用户重名");
        }else{
            sysUser.setUpdateTime(new Date());
            sysUser.setCreateTime(new Date());
            quUserMapper.insert(sysUser);
            return new ResponseResult(ResponseCode.SUCCESS, "注册成功");
        }
    }

    public ResponseResult getUserData(HttpServletRequest request){
        SysUser sysUser = SessionUtils.getUser();
        if(sysUser==null){
            return new ResponseResult(ResponseCode.SUCCESS, "查询成功");
        }
        NmBookCollect paramCollect = new NmBookCollect();
        paramCollect.setCreater(sysUser.getUid());
        paramCollect.setType(1l);
        paramCollect.setValidFlag(1l);
        QueryWrapper queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allCollect = nmBookCollectMapper.selectList(queryWrapper);

        paramCollect.setType(0l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allStar = nmBookCollectMapper.selectList(queryWrapper);

        paramCollect.setType(2l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allHis = nmBookCollectMapper.selectList(queryWrapper);

        paramCollect = new NmBookCollect();
        paramCollect.setUid(sysUser.getUid());
        paramCollect.setType(0l);
        paramCollect.setValidFlag(1l);
        queryWrapper = new QueryWrapper(paramCollect);
        List<NmBookCollect> allFans = nmBookCollectMapper.selectList(queryWrapper);


        Map<String,Object> res = new HashMap<>();
        res.put("allCollect",allCollect);
        res.put("allHis",allHis);
        res.put("allStar",allStar);
        res.put("allFans",allFans);

        return new ResponseResult(ResponseCode.SUCCESS, "查询成功",res);
    }

    public ResponseResult login(String nick,String pass) {
        SysUser sysUser = new SysUser();
        sysUser.setNickName(nick);
        sysUser.setPassword(pass);
        sysUser.setValidFlag(1l);
        QueryWrapper queryWrapper = new QueryWrapper(sysUser);
        sysUser = quUserMapper.selectOne(queryWrapper);
        if(sysUser !=null){
            SysUserLogin sysUserLogin = new SysUserLogin();
            sysUserLogin.setValidFlag(1l);
            sysUserLogin.setUid(sysUser.getUid());
            queryWrapper = new QueryWrapper(sysUserLogin);
            queryWrapper.orderByDesc("create_time");
            sysUserLogin = nmUserLoginMapper.selectOne(queryWrapper);
            String token = "";
            if (sysUserLogin == null) {
                sysUserLogin = new SysUserLogin();
                sysUserLogin.setValidFlag(1l);
                sysUserLogin.setUid(sysUser.getUid());
                token = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
                sysUserLogin.setUid(sysUser.getUid());
                sysUserLogin.setToken(token);
                sysUserLogin.setCreateTime(new Date());
                sysUserLogin.setCreater(sysUser.getUid());
                sysUserLogin.setUpdateTime(new Date());
                sysUserLogin.setUpdater(sysUser.getUid());
                nmUserLoginMapper.insert(sysUserLogin);
            } else {
                sysUserLogin.setUpdateTime(new Date());
                token = sysUserLogin.getToken();
                nmUserLoginMapper.updateById(sysUserLogin);
            }
            sysUser.setToken(token);
            return new ResponseResult(ResponseCode.SUCCESS, sysUser);
        }
        return new ResponseResult(ResponseCode.SERVICE_ERROR, "用户信息错误");
    }

    public ResponseResult saveFile(MultipartFile file) throws IOException {
        String[] fileTypeArr = file.getOriginalFilename().split("\\.");
        String fileType = fileTypeArr[fileTypeArr.length - 1];
        String newName = "audio-"+ UUID.randomUUID().toString()+"-"+new Date().getTime()+"."+fileType;
        file.transferTo(new File(SysInfo.FILE_PATH+newName));
        return new ResponseResult(ResponseCode.SUCCESS, "上传成功",newName);
    }
}
