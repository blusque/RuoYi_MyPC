package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.purchase.domain.PurchaseOrder;
import com.ruoyi.purchase.mapper.PurchaseOrderMapper;
import com.ruoyi.purchase.service.IPurchaseOrderService;

/**
 * 采购订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService 
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单主键
     * @return 采购订单
     */
    @Override
    public PurchaseOrder selectPurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.selectPurchaseOrderById(id);
    }

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
    }

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderByIds(Long[] ids)
    {
        return purchaseOrderMapper.deletePurchaseOrderByIds(ids);
    }

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.deletePurchaseOrderById(id);
    }
}
