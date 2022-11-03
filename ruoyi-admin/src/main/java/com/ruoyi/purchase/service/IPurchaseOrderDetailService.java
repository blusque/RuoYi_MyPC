package com.ruoyi.purchase.service;

import java.util.List;

import com.ruoyi.purchase.domain.PurchaseOrderDetail;

/**
 * 采购订单明细Service接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface IPurchaseOrderDetailService 
{
    /**
     * 查询采购订单明细
     * 
     * @param orderId 采购订单明细主键
     * @return 采购订单明细
     */
    public PurchaseOrderDetail selectPurchaseOrderDetailByOrderId(Long orderId);

    /**
     * 查询采购订单明细列表
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 采购订单明细集合
     */
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailList(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 新增采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    public int insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 修改采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    public int updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 批量删除采购订单明细
     * 
     * @param orderIds 需要删除的采购订单明细主键集合
     * @return 结果
     */
    public int deletePurchaseOrderDetailByOrderIds(Long[] orderIds);

    /**
     * 删除采购订单明细信息
     * 
     * @param orderId 采购订单明细主键
     * @return 结果
     */
    public int deletePurchaseOrderDetailByOrderId(Long orderId);
}
