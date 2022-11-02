package com.ruoyi.storage.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 盘点操作对象 storage_check
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 盘点单编号 */
    @Excel(name = "盘点单编号")
    private Long id;

    /** 盘点日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "盘点日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 操作员工编号 */
    @Excel(name = "操作员工编号")
    private Long staffId;

    /** 盘点明细信息 */
    private List<StorageCheckDetail> storageCheckDetailList;

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

    public List<StorageCheckDetail> getStorageCheckDetailList()
    {
        return storageCheckDetailList;
    }

    public void setStorageCheckDetailList(List<StorageCheckDetail> storageCheckDetailList)
    {
        this.storageCheckDetailList = storageCheckDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("staffId", getStaffId())
            .append("storageCheckDetailList", getStorageCheckDetailList())
            .toString();
    }
}
