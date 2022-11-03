package com.ruoyi.market_yan.mapper;

import java.util.List;
import com.ruoyi.market_yan.domain.MarketOrder;

/**
 * 订单管理Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-02
 */
public interface MarketOrderMapper
{
    /**
     * 查询订单管理
     *
     * @param id 订单管理主键
     * @return 订单管理
     */
    public MarketOrder selectMarketOrderById(Long id);

    /**
     * 查询订单管理列表
     *
     * @param marketOrder 订单管理
     * @return 订单管理集合
     */
    public List<MarketOrder> selectMarketOrderList(MarketOrder marketOrder);

    /**
     * 新增订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    public int insertMarketOrder(MarketOrder marketOrder);

    /**
     * 修改订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    public int updateMarketOrder(MarketOrder marketOrder);

    /**
     * 删除订单管理
     *
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteMarketOrderById(Long id);

    /**
     * 批量删除订单管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketOrderByIds(Long[] ids);
}
