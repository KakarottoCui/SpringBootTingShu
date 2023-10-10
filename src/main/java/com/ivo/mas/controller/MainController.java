package com.ivo.mas.controller;

import com.ivo.mas.service.MainService;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.SysInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import ja

    @RequestMapping("/getLocationInfo")
    @ResponseBody
    public ResponseResult getLocationInfo(@RequestParam String location){
        return mainService.getLocationInfo(location);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult getUserInfo(String nick,String pass){
        return mainService.login(nick,pass);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    @RequestMapping("/regist")
    @ResponseBody
    public ResponseResult registUserInfo(String data){
        return mainService.regist(data);
    }

    @RequestMapping("/getUserData")
    @ResponseBody
    public ResponseResult getUserData(HttpServletRequest request){
        return mainService.getUserData(request);
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
       return mainService.saveFile(file);
    }

    @RequestMapping(value = "/getFile/{type}/{path}")
    public void getPicById(@PathVariable int type,@PathVariable String path, HttpServletRequest request, HttpServletResponse response) {
        path = SysInfo.FILE_PATH+path;
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        FileInputStream objInputStream = null;
        ServletOutputStream objOutStream = null;
        String[] imgArr = path.split("\\.");
        String fileType = imgArr[imgArr.length-1];
        response.setContentType(type==0?"image/"+fileType:"audio/"+fileType);
        response.setHeader("Content-Disposition", "attachment;fileName="+new Date().getTime()+"."+fileType);
        try{
            objInputStream= new FileInputStream(path);
            objOutStream = response.getOutputStream();
            int aRead = 0;
            while ((aRead = objInputStream.read()) != -1 & objInputStream != null) {
                objOutStream.write(aRead);
            }
            objOutStream.flush();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                objOutStream.close();
            }catch (IOException e) {
            }
        }
    }
}
