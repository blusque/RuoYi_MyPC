package com.ruoyi.mrp.service;

import java.util.List;
import com.ruoyi.mrp.domain.MrpPurchase;

/**
 * 采购Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IMrpPurchaseService 
{
    /**
     * 查询采购
     * 
     * @param mrpPurchaseId 采购主键
     * @return 采购
     */
    public MrpPurchase selectMrpPurchaseByMrpPurchaseId(Long mrpPurchaseId);

    /**
     * 查询采购列表
     * 
     * @param mrpPurchase 采购
     * @return 采购集合
     */
    public List<MrpPurchase> selectMrpPurchaseList(MrpPurchase mrpPurchase);

    /**
     * 新增采购
     * 
     * @param mrpPurchase 采购
     * @return 结果
     */
    public int insertMrpPurchase(MrpPurchase mrpPurchase);

    /**
     * 修改采购
     * 
     * @param mrpPurchase 采购
     * @return 结果
     */
    public int updateMrpPurchase(MrpPurchase mrpPurchase);

    /**
     * 批量删除采购
     * 
     * @param mrpPurchaseIds 需要删除的采购主键集合
     * @return 结果
     */
    public int deleteMrpPurchaseByMrpPurchaseIds(Long[] mrpPurchaseIds);

    /**
     * 删除采购信息
     * 
     * @param mrpPurchaseId 采购主键
     * @return 结果
     */
    public int deleteMrpPurchaseByMrpPurchaseId(Long mrpPurchaseId);
}
