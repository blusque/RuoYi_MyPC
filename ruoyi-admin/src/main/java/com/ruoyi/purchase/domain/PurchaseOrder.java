package com.ruoyi.purchase.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单对象 purchase_order
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public class PurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购订单 */
    private Long id;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date start;

    /** 预定到达日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预定到达日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date end;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private Long supplierId;

    /** 采购员编号 */
    @Excel(name = "采购员编号")
    private Long staffId;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String purchaseOrderStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStart(Date start) 
    {
        this.start = start;
    }

    public Date getStart() 
    {
        return start;
    }
    public void setEnd(Date end) 
    {
        this.end = end;
    }

    public Date getEnd() 
    {
        return end;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setStaffId(Long staffId) 
    {
        this.staffId = staffId;
    }

    public Long getStaffId() 
    {
        return staffId;
    }
    public void setPurchaseOrderStatus(String purchaseOrderStatus) 
    {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public String getPurchaseOrderStatus() 
    {
        return purchaseOrderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("start", getStart())
            .append("end", getEnd())
            .append("supplierId", getSupplierId())
            .append("staffId", getStaffId())
            .append("purchaseOrderStatus", getPurchaseOrderStatus())
            .toString();
    }
}
