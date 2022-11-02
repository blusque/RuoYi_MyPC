package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketOrder;

/**
 * 销售订单Mapper接口
 * 
 * @author mei
 * @date 2022-10-29
 */
public interface MarketOrderMapper 
{
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
	 * 查询销售订单
	 * 
	 * @param id 销售订单主键
	 * @return 销售订单
	 */
	public List<MarketOrder> selectMarketOrderListByDate(Integer duration);

	/**
	 * 查询销售订单
	 * 
	 * @param id 销售订单主键
	 * @return 销售订单
	 */
	public List<MarketOrder> selectMarketOrderListByDDL(Integer duration);

	/**
	 * 查询销售订单列表
	 * 
	 * @param marketOrder 销售订单
	 * @return 销售订单集合
	 */
	public List<MarketOrder> selectMarketOrderList(MarketOrder marketOrder);

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
	 * 删除销售订单
	 * 
	 * @param id 销售订单主键
	 * @return 结果
	 */
	public int deleteMarketOrderById(Long id);

	/**
	 * 批量删除销售订单
	 * 
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteMarketOrderByIds(Long[] ids);
}
