package com.ruoyi.market_yan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 market_order
 *
 * @author ruoyi
 * @date 2022-11-02
 */
public class MarketOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private Long id;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private Long customerId;

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String address;

    /** 销售员编号 */
    @Excel(name = "销售员编号")
    private Long staffId;

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
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }

    public Long getStaffId()
    {
        return staffId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("status", getStatus())
            .append("customerId", getCustomerId())
            .append("address", getAddress())
            .append("staffId", getStaffId())
            .toString();
    }
}
