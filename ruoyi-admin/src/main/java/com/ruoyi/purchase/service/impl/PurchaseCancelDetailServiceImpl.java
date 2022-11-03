package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.purchase.domain.PurchaseCancelDetail;
import com.ruoyi.purchase.mapper.PurchaseCancelDetailMapper;
import com.ruoyi.purchase.service.IPurchaseCancelDetailService;

/**
 * 采购订单退货明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class PurchaseCancelDetailServiceImpl implements IPurchaseCancelDetailService 
{
    @Autowired
    private PurchaseCancelDetailMapper purchaseCancelDetailMapper;

    /**
     * 查询采购订单退货明细
     * 
     * @param orderId 采购订单退货明细主键
     * @return 采购订单退货明细
     */
    @Override
    public PurchaseCancelDetail selectPurchaseCancelDetailByOrderId(Long orderId)
    {
        return purchaseCancelDetailMapper.selectPurchaseCancelDetailByOrderId(orderId);
    }

    /**
     * 查询采购订单退货明细列表
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 采购订单退货明细
     */
    @Override
    public List<PurchaseCancelDetail> selectPurchaseCancelDetailList(PurchaseCancelDetail purchaseCancelDetail)
    {
        return purchaseCancelDetailMapper.selectPurchaseCancelDetailList(purchaseCancelDetail);
    }

    /**
     * 新增采购订单退货明细
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 结果
     */
    @Override
    public int insertPurchaseCancelDetail(PurchaseCancelDetail purchaseCancelDetail)
    {
        System.out.println(purchaseCancelDetail);
        return purchaseCancelDetailMapper.insertPurchaseCancelDetail(purchaseCancelDetail);
    }

    /**
     * 修改采购订单退货明细
     * 
     * @param purchaseCancelDetail 采购订单退货明细
     * @return 结果
     */
    @Override
    public int updatePurchaseCancelDetail(PurchaseCancelDetail purchaseCancelDetail)
    {
        return purchaseCancelDetailMapper.updatePurchaseCancelDetail(purchaseCancelDetail);
    }

    /**
     * 批量删除采购订单退货明细
     * 
     * @param orderIds 需要删除的采购订单退货明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseCancelDetailByOrderIds(Long[] orderIds)
    {
        return purchaseCancelDetailMapper.deletePurchaseCancelDetailByOrderIds(orderIds);
    }

    /**
     * 删除采购订单退货明细信息
     * 
     * @param orderId 采购订单退货明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseCancelDetailByOrderId(Long orderId)
    {
        return purchaseCancelDetailMapper.deletePurchaseCancelDetailByOrderId(orderId);
    }
}
