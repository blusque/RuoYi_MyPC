package com.ruoyi.sc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产工票对象 production_of_work_tickets
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class ProductionOfWorkTickets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 派工单号 */
    private Long mcnum;

    /** 工作中心代码 */
    @Excel(name = "工作中心代码")
    private String wcennunm;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfDemand;

    /** 派工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "派工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dd;

    /** 执行状况 */
    @Excel(name = "执行状况")
    private String ec;

    public void setMcnum(Long mcnum) 
    {
        this.mcnum = mcnum;
    }

    public Long getMcnum() 
    {
        return mcnum;
    }
    public void setWcennunm(String wcennunm) 
    {
        this.wcennunm = wcennunm;
    }

    public String getWcennunm() 
    {
        return wcennunm;
    }
    public void setDateOfDemand(Date dateOfDemand) 
    {
        this.dateOfDemand = dateOfDemand;
    }

    public Date getDateOfDemand() 
    {
        return dateOfDemand;
    }
    public void setDd(Date dd) 
    {
        this.dd = dd;
    }

    public Date getDd() 
    {
        return dd;
    }
    public void setEc(String ec) 
    {
        this.ec = ec;
    }

    public String getEc() 
    {
        return ec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mcnum", getMcnum())
            .append("wcennunm", getWcennunm())
            .append("dateOfDemand", getDateOfDemand())
            .append("dd", getDd())
            .append("ec", getEc())
            .toString();
    }
}
