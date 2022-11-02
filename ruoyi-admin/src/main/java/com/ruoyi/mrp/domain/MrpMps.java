package com.ruoyi.mrp.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * MPS对象 mrp_mps
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class MrpMps extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单号 */
    private Long productId;

    /** 生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productTime;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Long productNumber;

    /** 生产产品id */
    @Excel(name = "生产产品id")
    private Long productNameid;

    /** 生产产品名称 */
    @Excel(name = "生产产品名称")
    private String productName;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductTime(Date productTime) 
    {
        this.productTime = productTime;
    }

    public Date getProductTime() 
    {
        return productTime;
    }
    public void setProductNumber(Long productNumber) 
    {
        this.productNumber = productNumber;
    }

    public Long getProductNumber() 
    {
        return productNumber;
    }
    public void setProductNameid(Long productNameid) 
    {
        this.productNameid = productNameid;
    }

    public Long getProductNameid() 
    {
        return productNameid;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productTime", getProductTime())
            .append("productNumber", getProductNumber())
            .append("productNameid", getProductNameid())
            .append("productName", getProductName())
            .toString();
    }
}
