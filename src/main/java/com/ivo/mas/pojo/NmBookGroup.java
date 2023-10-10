package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * NmBookGroup表实体类
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NmBookGroup implements Serializable {
    private static final long serialVersionUID = -53698688302037169L;

    /**
     * 自增主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分组名称
     */    
    private String groupName;
    /**
     * 备注
     */    
    private String memo;
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


}

