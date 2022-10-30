package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售订单对象 market_order
 * 
 * @author mei
 * @date 2022-10-29
 */
public class MarketOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private Long id;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 订单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ddl;

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

    private List<MarketOrderDetail> marketOrderDetailList;

    private List<MarketCancelDetail> marketCancelDetailList;

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
    public void setDDL(Date ddl) 
    {
        this.ddl = ddl;
    }

    public Date getDDL() 
    {
        return ddl;
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

    public void setMarketOrderDetailList(List<MarketOrderDetail> marketOrderDetailList)
    {
        this.marketOrderDetailList = marketOrderDetailList;
    }

    public List<MarketOrderDetail> getMarketOrderDetailList()
    {
        return marketOrderDetailList;
    }

    public void setMarketCancelDetailList(List<MarketCancelDetail> marketCancelDetailList)
    {
        this.marketCancelDetailList = marketCancelDetailList;
    }

    public List<MarketCancelDetail> getMarketCancelDetailList()
    {
        return marketCancelDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("ddl", getDDL())
            .append("status", getStatus())
            .append("customerId", getCustomerId())
            .append("address", getAddress())
            .append("staffId", getStaffId())
            .append("marketOrderDetailList", getMarketOrderDetailList())
            .append("marketCancelDetailList", getMarketCancelDetailList())
            .toString();
    }
}
