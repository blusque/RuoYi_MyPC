package com.ruoyi.market_yan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market_yan.mapper.MarketYanOrderMapper;
import com.ruoyi.market_yan.domain.MarketYanOrder;
import com.ruoyi.market_yan.service.IMarketYanOrderService;

/**
 * 订单管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class MarketYanOrderServiceImpl implements IMarketYanOrderService
{
    @Autowired
    private MarketYanOrderMapper marketOrderMapper;

    /**
     * 查询订单管理
     *
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public MarketYanOrder selectMarketYanOrderById(Long id)
    {
        return marketOrderMapper.selectMarketYanOrderById(id);
    }

    /**
     * 查询订单管理列表
     *
     * @param marketOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<MarketYanOrder> selectMarketYanOrderList(MarketYanOrder marketOrder)
    {
        return marketOrderMapper.selectMarketYanOrderList(marketOrder);
    }

    /**
     * 新增订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertMarketYanOrder(MarketYanOrder marketOrder)
    {
        return marketOrderMapper.insertMarketYanOrder(marketOrder);
    }

    /**
     * 修改订单管理
     *
     * @param marketOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateMarketYanOrder(MarketYanOrder marketOrder)
    {
        return marketOrderMapper.updateMarketYanOrder(marketOrder);
    }

    /**
     * 批量删除订单管理
     *
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketYanOrderByIds(Long[] ids)
    {
        return marketOrderMapper.deleteMarketYanOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     *
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketYanOrderById(Long id)
    {
        return marketOrderMapper.deleteMarketYanOrderById(id);
    }
}
