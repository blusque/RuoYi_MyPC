package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车间对象 sys_factory
 * 
 * @author laiyuan
 * @date 2022-10-27
 */
public class SysFactory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车间编号 */
    private Long id;

    /** 加工位数量 */
    @Excel(name = "加工位数量")
    private Long centerNumber;

    /** 备注 */
    @Excel(name = "备注")
    private String comment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCenterNumber(Long centerNumber) 
    {
        this.centerNumber = centerNumber;
    }

    public Long getCenterNumber() 
    {
        return centerNumber;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("centerNumber", getCenterNumber())
            .append("comment", getComment())
            .toString();
    }
}
