package com.ruoyi.storage.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 移动操作对象 storage_move
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageMove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 移库单编号 */
    @Excel(name = "移库单编号")
    private Long id;

    /** 移库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "移库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 操作员工编号 */
    @Excel(name = "操作员工编号")
    private Long staffId;

    /** 移动明细信息 */
    private List<StorageMoveDetail> storageMoveDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setStaffId(Long staffId) 
    {
        this.staffId = staffId;
    }

    public Long getStaffId() 
    {
        return staffId;
    }

    public List<StorageMoveDetail> getStorageMoveDetailList()
    {
        return storageMoveDetailList;
    }

    public void setStorageMoveDetailList(List<StorageMoveDetail> storageMoveDetailList)
    {
        this.storageMoveDetailList = storageMoveDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("staffId", getStaffId())
            .append("storageMoveDetailList", getStorageMoveDetailList())
            .toString();
    }
}
