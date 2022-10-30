package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MarketOrderDetail;

/**
 * 订单明细Mapper接口
 * 
 * @author mei
 * @date 2022-10-29
 */
public interface MarketOrderDetailMapper {
    /**
     * 查询订单明细列表
     * 
     * @param marketOrderDetail 订单明细
     * @return 订单明细集合
     */
    public List<MarketOrderDetail> selectMarketOrderDetailList(MarketOrderDetail marketOrderDetail);

    /**
     * 新增订单明细
     * 
     * @param marketOrderDetail 订单明细
     * @return 结果
     */
    public int batchMarketOrderDetail(List<MarketOrderDetail> marketOrderDetail);

    /**
     * 修改订单明细
     * 
     * @param marketOrderDetail 订单明细
     * @return 结果
     */
    public int updateMarketOrderDetail(MarketOrderDetail marketOrderDetail);

    /**
     * 删除订单明细
     * 
     * @param marketorderDetail 订单明细
     * @return 结果
     */
    public int deleteMarketOrderDetailByOrderId(Long OrderId);

    /**
     * 删除订单明细
     * 
     * @param marketorderDetail 订单明细
     * @return 结果
     */
    public int deleteMarketOrderDetailByOrderIds(Long[] OrderIds);

    /**
     * 删除订单明细
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteMarketOrderDetailById(Long id);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketOrderDetailByIds(Long[] ids);
}
