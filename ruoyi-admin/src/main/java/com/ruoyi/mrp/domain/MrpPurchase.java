package com.ruoyi.mrp.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购对象 mrp_purchase
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class MrpPurchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购编号 */
    private Long mrpPurchaseId;

    /** 采购时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mrpPurchaseDate;

    /** 采购产品名称编号 */
    @Excel(name = "采购产品名称编号")
    private String mrpPurchaseNameid;

    /** 采购产品数量 */
    @Excel(name = "采购产品数量")
    private Long mrpPurchaseNumber;

    public void setMrpPurchaseId(Long mrpPurchaseId) 
    {
        this.mrpPurchaseId = mrpPurchaseId;
    }

    public Long getMrpPurchaseId() 
    {
        return mrpPurchaseId;
    }
    public void setMrpPurchaseDate(Date mrpPurchaseDate) 
    {
        this.mrpPurchaseDate = mrpPurchaseDate;
    }

    public Date getMrpPurchaseDate() 
    {
        return mrpPurchaseDate;
    }
    public void setMrpPurchaseNameid(String mrpPurchaseNameid) 
    {
        this.mrpPurchaseNameid = mrpPurchaseNameid;
    }

    public String getMrpPurchaseNameid() 
    {
        return mrpPurchaseNameid;
    }
    public void setMrpPurchaseNumber(Long mrpPurchaseNumber) 
    {
        this.mrpPurchaseNumber = mrpPurchaseNumber;
    }

    public Long getMrpPurchaseNumber() 
    {
        return mrpPurchaseNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mrpPurchaseId", getMrpPurchaseId())
            .append("mrpPurchaseDate", getMrpPurchaseDate())
            .append("mrpPurchaseNameid", getMrpPurchaseNameid())
            .append("mrpPurchaseNumber", getMrpPurchaseNumber())
            .toString();
    }
}
