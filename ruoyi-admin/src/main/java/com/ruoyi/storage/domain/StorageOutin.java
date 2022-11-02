package com.ruoyi.storage.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出入操作对象 storage_outin
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageOutin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出入库单编号 */
    @Excel(name = "出入库单编号")
    private Long id;

    /** 出/入库 */
    @Excel(name = "出/入库")
    private String outorin;

    /** 库单类型 */
    @Excel(name = "库单类型")
    private String kdtype;

    /** 出入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出入库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 操作员工编号 */
    @Excel(name = "操作员工编号")
    private Long staffId;

    /** 出入明细信息 */
    private List<StorageOutinDetail> storageOutinDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOutorin(String outorin) 
    {
        this.outorin = outorin;
    }

    public String getOutorin() 
    {
        return outorin;
    }
    public void setKdtype(String kdtype) 
    {
        this.kdtype = kdtype;
    }

    public String getKdtype() 
    {
        return kdtype;
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

    public List<StorageOutinDetail> getStorageOutinDetailList()
    {
        return storageOutinDetailList;
    }

    public void setStorageOutinDetailList(List<StorageOutinDetail> storageOutinDetailList)
    {
        this.storageOutinDetailList = storageOutinDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("outorin", getOutorin())
            .append("kdtype", getKdtype())
            .append("date", getDate())
            .append("staffId", getStaffId())
            .append("storageOutinDetailList", getStorageOutinDetailList())
            .toString();
    }
}
