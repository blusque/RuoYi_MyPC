package com.ruoyi.market.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户地址对象 customer_address
 * 
 * @author mei
 * @date 2022-10-28
 */
public class CustomerAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户编号 */
    private Long customerId;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String customerAddress;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    
    public void setCustomerAddress(String customerAddress) 
    {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() 
    {
        return customerAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerAddress", getCustomerAddress())
            .toString();
    }
}
