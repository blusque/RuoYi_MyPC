package com.ruoyi.sc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 派工单子对象 dispatch
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class Dispatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 派工单号 */
    private Long mcnum;

    /** 工序序号 */
    @Excel(name = "工序序号")
    private Long wpronum;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Long 
productionQuantity;

    /** 任务号 */
    @Excel(name = "任务号")
    private Long tasknum;

    public void setMcnum(Long mcnum) 
    {
        this.mcnum = mcnum;
    }

    public Long getMcnum() 
    {
        return mcnum;
    }
    public void setWpronum(Long wpronum) 
    {
        this.wpronum = wpronum;
    }

    public Long getWpronum() 
    {
        return wpronum;
    }
    public void setproductionQuantity(Long productionQuantity) 
    {
        this.
productionQuantity = 
productionQuantity;
    }

    public Long getproductionQuantity() 
    {
        return 
productionQuantity;
    }
    public void setTasknum(Long tasknum) 
    {
        this.tasknum = tasknum;
    }

    public Long getTasknum() 
    {
        return tasknum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mcnum", getMcnum())
            .append("wpronum", getWpronum())
            .append("productionQuantity", getproductionQuantity())
            .append("tasknum", getTasknum())
            .toString();
    }
}
