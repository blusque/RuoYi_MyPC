package com.ruoyi.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户管理对象 customer
 * 
 * @author mei
 * @date 2022-10-27
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户编号 */
    private Long id;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String tel;

    /** 客户邮件 */
    @Excel(name = "客户邮件")
    private String email;

    /** 客户评级 */
    @Excel(name = "客户评级")
    private Double customerLevel;

    /** 客户标签 */
    @Excel(name = "客户标签")
    private String customerTags;

    /** 客户地址信息 */
    private List<CustomerAddress> customerAddressList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setCustomerLevel(Double customerLevel) 
    {
        this.customerLevel = customerLevel;
    }

    public Double getCustomerLevel() 
    {
        return customerLevel;
    }
    public void setCustomerTags(String customerTags) 
    {
        this.customerTags = customerTags;
    }

    public String getCustomerTags() 
    {
        return customerTags;
    }

    public List<CustomerAddress> getCustomerAddressList()
    {
        return customerAddressList;
    }

    public void setCustomerAddressList(List<CustomerAddress> customerAddressList)
    {
        this.customerAddressList = customerAddressList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tel", getTel())
            .append("email", getEmail())
            .append("customerLevel", getCustomerLevel())
            .append("customerTags", getCustomerTags())
            .append("customerAddressList", getCustomerAddressList())
            .toString();
    }
}
