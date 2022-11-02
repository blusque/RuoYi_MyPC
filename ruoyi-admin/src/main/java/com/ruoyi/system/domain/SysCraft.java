package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工艺管理对象 sys_craft
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public class SysCraft extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工艺编号 */
    private Long id;

    /** 工艺名称 */
    @Excel(name = "工艺名称")
    private String craftName;

    /** 子物料 */
    @Excel(name = "子物料")
    private Long materialId;

    /** 父物料 */
    @Excel(name = "父物料")
    private Long parentId;

    /** 预计耗时(单件) */
    @Excel(name = "预计耗时(单件)")
    private Long duration;

    /** 车间编号 */
    @Excel(name = "车间编号")
    private Long factoryId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCraftName(String craftName) 
    {
        this.craftName = craftName;
    }

    public String getCraftName() 
    {
        return craftName;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }
    public void setFactoryId(Long factoryId) 
    {
        this.factoryId = factoryId;
    }

    public Long getFactoryId() 
    {
        return factoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("craftName", getCraftName())
            .append("materialId", getMaterialId())
            .append("parentId", getParentId())
            .append("duration", getDuration())
            .append("factoryId", getFactoryId())
            .toString();
    }
}
