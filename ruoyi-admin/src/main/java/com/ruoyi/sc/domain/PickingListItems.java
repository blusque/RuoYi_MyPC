package com.ruoyi.sc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领料单物料对象 picking_list_items
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class PickingListItems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 领料单号 */
    private Long mrnum;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialCode;

    /** 需求数量 */
    @Excel(name = "需求数量")
    private Long quantitydemande;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliverydate;

    public void setMrnum(Long mrnum) 
    {
        this.mrnum = mrnum;
    }

    public Long getMrnum() 
    {
        return mrnum;
    }
    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
    }
    public void setQuantitydemande(Long quantitydemande) 
    {
        this.quantitydemande = quantitydemande;
    }

    public Long getQuantitydemande() 
    {
        return quantitydemande;
    }
    public void setDeliverydate(Date deliverydate) 
    {
        this.deliverydate = deliverydate;
    }

    public Date getDeliverydate() 
    {
        return deliverydate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mrnum", getMrnum())
            .append("materialCode", getMaterialCode())
            .append("quantitydemande", getQuantitydemande())
            .append("deliverydate", getDeliverydate())
            .toString();
    }
}
