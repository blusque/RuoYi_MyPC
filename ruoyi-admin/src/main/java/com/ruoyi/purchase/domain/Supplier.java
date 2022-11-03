package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 supplier
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编号 */
    private Long id;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String name;

    /** 供应商电话 */
    @Excel(name = "供应商电话")
    private String tel;

    /** 供应商邮件 */
    @Excel(name = "供应商邮件")
    private String email;

    /** 供应商评级 */
    @Excel(name = "供应商评级")
    private Long level;

    /** 供应商折扣 */
    @Excel(name = "供应商折扣")
    private Long discount;

    /** 供应商地址 */
    @Excel(name = "供应商地址")
    private String address;

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
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setDiscount(Long discount) 
    {
        this.discount = discount;
    }

    public Long getDiscount() 
    {
        return discount;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tel", getTel())
            .append("email", getEmail())
            .append("level", getLevel())
            .append("discount", getDiscount())
            .append("address", getAddress())
            .toString();
    }
}
