package com.ruoyi.storage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 盘点明细对象 storage_check_detail
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageCheckDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 盘点单编号 */
    @Excel(name = "盘点单编号")
    private Long checkId;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private Long materialId;

    /** 库位编号 */
    @Excel(name = "库位编号")
    private Long storageId;

    /** 盈亏数量 */
    @Excel(name = "盈亏数量")
    private Long number;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCheckId(Long checkId) 
    {
        this.checkId = checkId;
    }

    public Long getCheckId() 
    {
        return checkId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setStorageId(Long storageId) 
    {
        this.storageId = storageId;
    }

    public Long getStorageId() 
    {
        return storageId;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkId", getCheckId())
            .append("materialId", getMaterialId())
            .append("storageId", getStorageId())
            .append("number", getNumber())
            .toString();
    }
}
