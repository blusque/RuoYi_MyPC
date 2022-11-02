package com.ruoyi.mrp.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产对象 mrp_produce
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class MrpProduce extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生产编号 */
    private Long mrpProduceId;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mrpProduceDate;

    /** 生产产品名称编号 */
    @Excel(name = "生产产品名称编号")
    private String mrpProduceNameid;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Long mrpProduceNumber;

    public void setMrpProduceId(Long mrpProduceId) 
    {
        this.mrpProduceId = mrpProduceId;
    }

    public Long getMrpProduceId() 
    {
        return mrpProduceId;
    }
    public void setMrpProduceDate(Date mrpProduceDate) 
    {
        this.mrpProduceDate = mrpProduceDate;
    }

    public Date getMrpProduceDate() 
    {
        return mrpProduceDate;
    }
    public void setMrpProduceNameid(String mrpProduceNameid) 
    {
        this.mrpProduceNameid = mrpProduceNameid;
    }

    public String getMrpProduceNameid() 
    {
        return mrpProduceNameid;
    }
    public void setMrpProduceNumber(Long mrpProduceNumber) 
    {
        this.mrpProduceNumber = mrpProduceNumber;
    }

    public Long getMrpProduceNumber() 
    {
        return mrpProduceNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mrpProduceId", getMrpProduceId())
            .append("mrpProduceDate", getMrpProduceDate())
            .append("mrpProduceNameid", getMrpProduceNameid())
            .append("mrpProduceNumber", getMrpProduceNumber())
            .toString();
    }
}
