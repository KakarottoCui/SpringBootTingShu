package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * NmBookCollect表实体类
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NmBookCollect implements Serializable {
    private static final long serialVersionUID = 722297625026264242L;

    /**
     * 自增主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 类型，0关注，1收藏，2历史
     */    
    private Long type;
    /**
     * 作者id
     */    
    private Long uid;
    /**
     * 作品id
     */    
    private Long bid;
    /**
     * 创建人id，作者
     */    
    private Long creater;
    /**
     * 创建时间
     */    
    private Date createTime;
    /**
     * 有效标识
     */    
    private Long validFlag;
    /**
     * 更新人id
     */    
    private Long updater;
    /**
     * 更新时间
     */    
    private Date updateTime;
    /**
     * 作品名称
     */    
    private String bookName;
    /**
     * 作者名称
     */
    private String uname;



}

