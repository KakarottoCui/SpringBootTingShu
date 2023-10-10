package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * SysUser表实体类
 * 用户表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = 113406666266030161L;

    /**
     * 用户id,自增
     */
    @TableId(type = IdType.AUTO)
    private Long uid;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别，0女1男
     */
    private Long sex;
    /**
     * 所在地
     */
    private String address;
    /**
     * 头像地址
     */
    private String pic;
    /**
     * 微信用户唯一ID
     */
    private String openId;
    /**
     * 有效标识
     */
    private Long validFlag;
    /**
     * 创建人id
     */
    private Long creater;
    /**
     * 更新人id
     */
    private Long updater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注，问题说明
     */
    private String memo;
    /**
     * 用户加密ID
     */
    private String sessionKey;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型，1管理员,2作者，3读者
     */
    private Long type;

    @TableField(exist = false)
    private String token;


}

