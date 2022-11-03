package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.purchase.domain.PurchaseOrderDetail;
import com.ruoyi.purchase.mapper.PurchaseOrderDetailMapper;
import com.ruoyi.purchase.service.IPurchaseOrderDetailService;

/**
 * 采购订单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class PurchaseOrderDetailServiceImpl implements IPurchaseOrderDetailService 
{
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;

    /**
     * 查询采购订单明细
     * 
     * @param orderId 采购订单明细主键
     * @return 采购订单明细
     */
    @Override
    public PurchaseOrderDetail selectPurchaseOrderDetailByOrderId(Long orderId)
    {
        return purchaseOrderDetailMapper.selectPurchaseOrderDetailByOrderId(orderId);
    }

    /**
     * 查询采购订单明细列表
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 采购订单明细
     */
    @Override
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailList(PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailMapper.selectPurchaseOrderDetailList(purchaseOrderDetail);
    }

    /**
     * 新增采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    @Override
    public int insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailMapper.insertPurchaseOrderDetail(purchaseOrderDetail);
    }

    /**
     * 修改采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    @Override
    public int updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailMapper.updatePurchaseOrderDetail(purchaseOrderDetail);
    }

    /**
     * 批量删除采购订单明细
     * 
     * @param orderIds 需要删除的采购订单明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderDetailByOrderIds(Long[] orderIds)
    {
        return purchaseOrderDetailMapper.deletePurchaseOrderDetailByOrderIds(orderIds);
    }

    /**
     * 删除采购订单明细信息
     * 
     * @param orderId 采购订单明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderDetailByOrderId(Long orderId)
    {
        return purchaseOrderDetailMapper.deletePurchaseOrderDetailByOrderId(orderId);
    }
}
