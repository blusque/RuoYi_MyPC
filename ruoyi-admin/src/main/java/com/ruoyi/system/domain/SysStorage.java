package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库管理对象 sys_storage
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public class SysStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库编号 */
    private Long id;

    /** 仓库容量 */
    @Excel(name = "仓库容量")
    private Long capacity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("capacity", getCapacity())
            .toString();
    }
}
