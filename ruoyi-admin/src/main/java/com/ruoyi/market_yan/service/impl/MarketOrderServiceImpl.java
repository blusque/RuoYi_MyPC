package com.ruoyi.market_yan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market_yan.mapper.MarketOrderMapper;
import com.ruoyi.market_yan.domain.MarketOrder;
import com.ruoyi.market_yan.service.IMarketOrderService;

/**
 * 订单管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class MarketOrderServiceImpl implements IMarketOrderService
{
    @Autowired
    private MarketOrderMapper marketOrderMapper;

    /**
     * 查询订单管理
     *
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public MarketOrder selectMarketOrderById(Long id)
    {
        return marketOrderMapper.selectMarketOrderById(id);
    }

    /**
     * 查询订单管理列表
     *
     * @param marketOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<MarketOrder> selectMarketOrderList(MarketOrder marketOrder)
    {
        return marketOrderMapper.selectMarketOrderList(marketOrder);
    }

    /**
     * 新增订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertMarketOrder(MarketOrder marketOrder)
    {
        return marketOrderMapper.insertMarketOrder(marketOrder);
    }

    /**
     * 修改订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateMarketOrder(MarketOrder marketOrder)
    {
        return marketOrderMapper.updateMarketOrder(marketOrder);
    }

    /**
     * 批量删除订单管理
     *
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketOrderByIds(Long[] ids)
    {
        return marketOrderMapper.deleteMarketOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     *
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketOrderById(Long id)
    {
        return marketOrderMapper.deleteMarketOrderById(id);
    }
}
