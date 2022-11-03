package com.ruoyi.market_yan.service;

import java.util.List;
import com.ruoyi.market_yan.domain.MarketYanOrder;

/**
 * 订单管理Service接口
 *
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IMarketYanOrderService
{
    /**
     * 查询订单管理
     *
     * @param id 订单管理主键
     * @return 订单管理
     */
    public MarketYanOrder selectMarketYanOrderById(Long id);

    /**
     * 查询订单管理列表
     *
     * @param marketOrder 订单管理
     * @return 订单管理集合
     */
    public List<MarketYanOrder> selectMarketYanOrderList(MarketYanOrder marketOrder);

    /**
     * 新增订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    public int insertMarketYanOrder(MarketYanOrder marketOrder);

    /**
     * 修改订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    public int updateMarketYanOrder(MarketYanOrder marketOrder);

    /**
     * 批量删除订单管理
     *
     * @param ids 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteMarketYanOrderByIds(Long[] ids);

    /**
     * 删除订单管理信息
     *
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteMarketYanOrderById(Long id);
}
