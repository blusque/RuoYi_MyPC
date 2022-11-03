package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单退货明细对象 purchase_cancel_detail
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public class PurchaseCancelDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private Long orderId;

    /** 物料编号 */
    private Long materialId;

    /** 物料数量 */
    @Excel(name = "物料数量")
    private Long number;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("materialId", getMaterialId())
            .append("number", getNumber())
            .toString();
    }
}
