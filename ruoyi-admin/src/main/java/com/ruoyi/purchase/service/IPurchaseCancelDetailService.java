package com.ruoyi.purchase.service;

import java.util.List;

import com.ruoyi.purchase.domain.PurchaseCancelDetail;

/**
 * 采购订单退货明细Service接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface IPurchaseCancelDetailService 
{
    /**
     * 查询采购订单退货明细
     * 
     * @param orderId 采购订单退货明细主键
     * @return 采购订单退货明细
     */
    public PurchaseCancelDetail selectPurchaseCancelDetailByOrderId(Long orderId);

    /**
     * 查询采购订单退货明细列表
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 采购订单退货明细集合
     */
    public List<PurchaseCancelDetail> selectPurchaseCancelDetailList(PurchaseCancelDetail purchaseCancelDetail);

    /**
     * 新增采购订单退货明细
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 结果
     */
    public int insertPurchaseCancelDetail(PurchaseCancelDetail purchaseCancelDetail);

    /**
     * 修改采购订单退货明细
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 结果
     */
    public int updatePurchaseCancelDetail(PurchaseCancelDetail purchaseCancelDetail);

    /**
     * 批量删除采购订单退货明细
     * 
     * @param orderIds 需要删除的采购订单退货明细主键集合
     * @return 结果
     */
    public int deletePurchaseCancelDetailByOrderIds(Long[] orderIds);

    /**
     * 删除采购订单退货明细信息
     * 
     * @param orderId 采购订单退货明细主键
     * @return 结果
     */
    public int deletePurchaseCancelDetailByOrderId(Long orderId);
}
