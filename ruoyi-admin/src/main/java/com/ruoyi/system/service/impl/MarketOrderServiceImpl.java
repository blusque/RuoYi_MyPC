package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.MarketOrderDetailMapper;
import com.ruoyi.system.mapper.MarketCancelDetailMapper;
import com.ruoyi.system.mapper.MarketOrderMapper;
import com.ruoyi.system.domain.MarketOrder;
import com.ruoyi.system.domain.MarketOrderDetail;
import com.ruoyi.system.domain.MarketCancelDetail;
import com.ruoyi.system.service.IMarketOrderService;

/**
 * 销售订单Service业务层处理
 * 
 * @author mei
 * @date 2022-10-29
 */
@Service
public class MarketOrderServiceImpl implements IMarketOrderService {
    @Autowired
    private MarketOrderMapper marketOrderMapper;

    @Autowired
    private MarketOrderDetailMapper marketOrderDetailMapper;

    @Autowired
    private MarketCancelDetailMapper marketCancelDetailMapper;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    @Override
    public MarketOrder selectMarketOrderById(Long id) {
        return marketOrderMapper.selectMarketOrderById(id);
    }

    /**
     * 查询销售订单
     * 
     * @param ids 销售订单主键集合
     * @return 销售订单
     */
    @Override
    public List<MarketOrder> selectMarketOrderListByIds(Long[] ids) {
        return marketOrderMapper.selectMarketOrderListByIds(ids);
    }

    /**
     * 查询销售订单列表
     * 
     * @param marketOrder 销售订单
     * @return 销售订单
     */
    @Override
    public List<MarketOrder> selectMarketOrderList(MarketOrder marketOrder) {
        List<MarketOrderDetail> detailList = marketOrder.getMarketOrderDetailList();
        List<MarketOrder> result = new ArrayList<MarketOrder>();
        if (detailList == null || detailList.size() == 0) {
            return marketOrderMapper.selectMarketOrderList(marketOrder);
        }
        else if (detailList.size() == 1) {
            System.out.println(detailList);
            MarketOrderDetail material = detailList.get(0);
            List<MarketOrderDetail> selected = marketOrderDetailMapper.selectMarketOrderDetailList(material);
            List<Long> ids = new ArrayList<Long>();
            for (MarketOrderDetail detail : selected) {
                ids.add(detail.getOrderId());
            }
            System.out.println(ids);
            List<MarketOrder> orderList = marketOrderMapper.selectMarketOrderList(marketOrder);
            if (orderList == null || orderList.size() == 0) {
                return result;
            }
            System.out.println(orderList);
            Long idMin = orderList.get(0).getId();
            Long idMax = orderList.get(orderList.size() - 1).getId();
            for (Long id : ids) {
                if (id < idMin || id > idMax) continue;
                System.out.println(id);
                int position = dividedSearch(orderList, 0, orderList.size(), id);
                System.out.println(position);
                if (position >= 0) result.add(orderList.get(position));
            }
            return result;
        }
        return result;
    }

    public int dividedSearch(List<MarketOrder> list, int start, int end, Long target) {
        if (start == end) {
            if (target == list.get(start).getId()) {
                return start;
            }
            else {
                return -1;
            }
        }
        else if (start == end - 1) {
            if (target == list.get(start).getId()) {
                return start;
            }
            else if (target == list.get(end).getId()) {
                return end;
            }
            else {
                return -1;
            }
        }
        int mid = (start + end) / 2;
        if (target > list.get(mid).getId()) {
            return dividedSearch(list, mid + 1, end, target);
        }
        else if (target < list.get(mid).getId()) {
            return dividedSearch(list, start, mid - 1, target);
        }
        else {
            return mid;
        }
    }

    /**
     * 新增销售订单
     * 
     * @param marketOrder 销售订单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMarketOrder(MarketOrder marketOrder) {
        int row = marketOrderMapper.insertMarketOrder(marketOrder);
        List<MarketOrderDetail> temp = marketOrder.getMarketOrderDetailList();
        marketOrder.setDate(null);
        marketOrder = marketOrderMapper.selectMarketOrderList(marketOrder).get(0);
        marketOrder.setMarketOrderDetailList(temp);
        insertMarketOrderDetail(marketOrder);
        return row;
    }

    /**
     * 修改销售订单
     * 
     * @param marketOrder 销售订单
     * @return 结果
     */
    @Override
    public int updateMarketOrder(MarketOrder marketOrder) {
        return marketOrderMapper.updateMarketOrder(marketOrder);
    }

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMarketOrderByIds(Long[] ids) {
        marketOrderDetailMapper.deleteMarketOrderDetailByIds(ids);
        marketCancelDetailMapper.deleteMarketCancelDetailByIds(ids);
        return marketOrderMapper.deleteMarketOrderByIds(ids);
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMarketOrderById(Long id) {
        marketOrderDetailMapper.deleteMarketOrderDetailByOrderId(id);
        marketCancelDetailMapper.deleteMarketCancelDetailById(id);
        return marketOrderMapper.deleteMarketOrderById(id);
    }

    /**
     * 查询订单明细列表
     * 
     * @param marketOrderDetail 订单明细
     * @return 订单明细集合
     */
    @Override
    public List<MarketOrderDetail> selectMarketOrderDetailList(MarketOrderDetail marketOrderDetail) {
        return marketOrderDetailMapper.selectMarketOrderDetailList(marketOrderDetail);
    }

    /**
     * 新增订单明细
     * 
     * @param marketOrderDetail 订单明细
     * @return 结果
     */
    @Override
    public int insertMarketOrderDetail(List<MarketOrderDetail> marketOrderDetail) {
        return marketOrderDetailMapper.batchMarketOrderDetail(marketOrderDetail);
    }

    public void insertMarketOrderDetail(MarketOrder marketOrder) {
        List<MarketOrderDetail> marketOrderDetailList = marketOrder.getMarketOrderDetailList();
        Long id = marketOrder.getId();
        List<MarketOrderDetail> list = new ArrayList<MarketOrderDetail>();
        for (MarketOrderDetail marketOrderDetail : marketOrderDetailList) {
            marketOrderDetail.setOrderId(id);
            list.add(marketOrderDetail);
        }
        insertMarketOrderDetail(list);
    }

    /**
     * 修改订单明细
     * 
     * @param marketOrderDetail 订单明细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMarketOrderDetail(List<MarketOrderDetail> marketOrderDetailList) {
        Long orderId = marketOrderDetailList.get(0).getOrderId();
        marketOrderDetailMapper.deleteMarketOrderDetailByOrderId(orderId);
        List<MarketOrderDetail> list = new ArrayList<MarketOrderDetail>();
        for (MarketOrderDetail marketOrderDetail : marketOrderDetailList) {
            marketOrderDetail.setOrderId(orderId);
            list.add(marketOrderDetail);
        }
        return insertMarketOrderDetail(list);
    }

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细主键集合
     * @return 结果
     */
    @Override
    public int deleteMarketOrderDetailByIds(Long[] ids) {
        return marketOrderDetailMapper.deleteMarketOrderDetailByIds(ids);
    }

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细主键
     * @return 结果
     */
    @Override
    public int deleteMarketOrderDetailById(Long id) {
        return marketOrderDetailMapper.deleteMarketOrderDetailById(id);
    }

    /**
     * 查询退货明细列表
     * 
     * @param marketCancelDetail 退货明细
     * @return 退货明细集合
     */
    @Override
    public List<MarketCancelDetail> selectMarketCancelDetailList(MarketCancelDetail marketCancelDetail) {
        return marketCancelDetailMapper.selectMarketCancelDetailList(marketCancelDetail);
    }

    /**
     * 新增退货明细, 前端保证marketCancelDetail一定含有order_id
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    @Override
    public int insertMarketCancelDetail(List<MarketCancelDetail> marketCancelDetailList) {
        return marketCancelDetailMapper.batchMarketCancelDetail(marketCancelDetailList);
    }

    /**
     * 修改退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMarketCancelDetail(List<MarketCancelDetail> marketCancelDetailList) {
        Long orderId = marketCancelDetailList.get(0).getOrderId();
        marketCancelDetailMapper.deleteMarketCancelDetailByOrderId(orderId);
        List<MarketCancelDetail> list = new ArrayList<MarketCancelDetail>();
        for (MarketCancelDetail marketCancelDetail : marketCancelDetailList) {
            marketCancelDetail.setOrderId(orderId);
            list.add(marketCancelDetail);
        }
        return insertMarketCancelDetail(list);
    }

    /**
     * 批量删除退货明细
     * 
     * @param ids 需要删除的退货明细主键集合
     * @return 结果
     */
    public int deleteMarketCancelDetailByIds(Long[] ids) {
        return marketCancelDetailMapper.deleteMarketCancelDetailByIds(ids);
    }

    /**
     * 删除退货明细信息
     * 
     * @param id 退货明细主键
     * @return 结果
     */
    public int deleteMarketCancelDetailById(Long id) {
        return marketOrderDetailMapper.deleteMarketOrderDetailById(id);
    }
}
