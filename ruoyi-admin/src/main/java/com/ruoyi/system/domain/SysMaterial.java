package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料管理对象 sys_mater
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public class SysMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料编号 */
    private Long id;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String name;

    /** 物料名称 */
    @Excel(name = "物料分类")
    private String property;

    /** 获取途径 */
    @Excel(name = "获取途径")
    private String from;

    /** 预计用时 */
    @Excel(name = "预计用时")
    private Long duration;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProperty(String property) 
    {
        this.property = property;
    }

    public String getProperty() 
    {
        return property;
    }
    public void setFrom(String from) 
    {
        this.from = from;
    }

    public String getFrom() 
    {
        return from;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("property", getProperty())
            .append("from", getFrom())
            .append("duration", getDuration())
            .toString();
    }
}
