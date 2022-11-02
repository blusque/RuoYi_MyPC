package com.ruoyi.storage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 移动明细对象 storage_move_detail
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public class StorageMoveDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 移库单编号 */
    @Excel(name = "移库单编号")
    private Long moveId;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private Long materialId;

    /** 移库数量 */
    @Excel(name = "移库数量")
    private Long number;

    /** 原始库位 */
    @Excel(name = "原始库位")
    private Long from;

    /** 目标库位 */
    @Excel(name = "目标库位")
    private Long to;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMoveId(Long moveId) 
    {
        this.moveId = moveId;
    }

    public Long getMoveId() 
    {
        return moveId;
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
    public void setFrom(Long from) 
    {
        this.from = from;
    }

    public Long getFrom() 
    {
        return from;
    }
    public void setTo(Long to) 
    {
        this.to = to;
    }

    public Long getTo() 
    {
        return to;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("moveId", getMoveId())
            .append("materialId", getMaterialId())
            .append("number", getNumber())
            .append("from", getFrom())
            .append("to", getTo())
            .toString();
    }
}
