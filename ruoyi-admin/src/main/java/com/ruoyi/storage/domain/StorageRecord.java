package com.ruoyi.storage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存信息对象 storage_record
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private Long materialId;

    /** 库位编号 */
    @Excel(name = "库位编号")
    private Long storageId;

    /** 存储数量 */
    @Excel(name = "存储数量")
    private Long number;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
            .append("materialId", getMaterialId())
            .append("storageId", getStorageId())
            .append("number", getNumber())
            .toString();
    }
}
