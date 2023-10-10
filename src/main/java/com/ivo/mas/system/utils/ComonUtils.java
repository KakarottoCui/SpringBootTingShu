package com.ivo.mas.system.utils;
import com.ivo.mas.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ComonUtils {

    public final static Map<String, String> CONVERT_MAP = new HashMap<String, String>() {
        {
            put("single", "单选题");
            put("mulity", "多选题");
            put("judge", "判断题");
            put("shortAnswer", "简答题");
            put("completion", "填空题");
            put("photoLib", "图片试题");
            put("cloze", "完形填空");
            put("reading", "阅读理解");
        }
    };

    public static void CheckPath(String path){
        File folder = new File(path);
        // 文件夹路径不存在
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void CheckFile(String path){
        File file = new File(path);
        // 文件夹路径不存在
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SysUser getLoginUser(HttpServletRequest request){
        return (SysUser)request.getSession().getAttribute("user");
    }

}
