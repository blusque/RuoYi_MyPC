package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.FromTo;
import com.ruoyi.market.domain.MarketOrder;
import com.ruoyi.market.domain.MarketCancelDetail;
import com.ruoyi.market.domain.MarketOrderDetail;

/**
 * 销售订单Service接口
 * 
 * @author mei
 * @date 2022-10-29
 */
public interface IMarketOrderService {
    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    public MarketOrder selectMarketOrderById(Long id);

    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    public List<MarketOrder> selectMarketOrderListByIds(Long[] ids);

    /**
     * 查询销售订单列表
     * 
     * @param marketOrder 销售订单
     * @return 销售订单集合
     */
    public List<MarketOrder> selectMarketOrderList(MarketOrder marketOrder);


    public List<MarketOrder> selectMarketOrderListFromTo(FromTo fromTo);

    /**
     * 新增销售订单
     * 
     * @param marketOrder 销售订单
     * @return 结果
     */
    public int insertMarketOrder(MarketOrder marketOrder);

    /**
     * 修改销售订单
     * 
     * @param marketOrder 销售订单
     * @return 结果
     */
    public int updateMarketOrder(MarketOrder marketOrder);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键集合
     * @return 结果
     */
    public int deleteMarketOrderByIds(Long[] ids);

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    public int deleteMarketOrderById(Long id);

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
    public int insertMarketOrderDetail(List<MarketOrderDetail> marketOrderDetailList);

    /**
     * 修改订单明细
     * 
     * @param marketOrderDetail 订单明细
     * @return 结果
     */
    public int updateMarketOrderDetail(List<MarketOrderDetail> marketOrderDetailList);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    public int deleteMarketOrderDetailByIds(Long[] ids);

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    public int deleteMarketOrderDetailById(Long id);

    /**
     * 查询退货明细列表
     * 
     * @param marketCancelDetail 退货明细
     * @return 退货明细集合
     */
    public List<MarketCancelDetail> selectMarketCancelDetailList(MarketCancelDetail marketCancelDetail);

    /**
     * 新增退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    public int insertMarketCancelDetail(List<MarketCancelDetail> marketCancelDetailList);

    /**
     * 修改退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    public int updateMarketCancelDetail(List<MarketCancelDetail> marketCancelDetailList);

    /**
     * 批量删除退货明细
     * 
     * @param ids 需要删除的退货明细主键集合
     * @return 结果
     */
    public int deleteMarketCancelDetailByIds(Long[] ids);

    /**
     * 删除退货明细信息
     * 
     * @param id 退货明细主键
     * @return 结果
     */
    public int deleteMarketCancelDetailById(Long id);

    public void dailyCheckOrderStatus();
}
