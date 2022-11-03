package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商明细对象 supplier_detail
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public class SupplierDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编号 */
    private Long supplierId;

    /** 物料编号 */
    private Long materialId;

    private Long productivity;

    private Long credits;

    private Long comprehensivepoints;

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }

    public void setproductivity(Long productivity) 
    {
        this.productivity = productivity;
    }

    public Long getproductivity() 
    {
        return productivity;
    }
    
    public void setcredits(Long credits) 
    {
        this.credits = credits;
    }

    public Long getcredits() 
    {
        return credits;
    }
    
    public void setcomprehensivepoints(Long comprehensivepoints) 
    {
        this.comprehensivepoints = comprehensivepoints;
    }

    public Long getcomprehensivepoints() 
    {
        return comprehensivepoints;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("materialId", getMaterialId())
            .append("productivity", getproductivity())
            .append("credits", getcredits())
            .append("comprehensivepoints", getcomprehensivepoints())
            .toString();
    }
}
