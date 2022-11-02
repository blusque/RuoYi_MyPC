package com.ruoyi.mrp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mrp.mapper.MrpPurchaseMapper;
import com.ruoyi.mrp.domain.MrpPurchase;
import com.ruoyi.mrp.service.IMrpPurchaseService;

/**
 * 采购Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class MrpPurchaseServiceImpl implements IMrpPurchaseService 
{
    @Autowired
    private MrpPurchaseMapper mrpPurchaseMapper;

    /**
     * 查询采购
     * 
     * @param mrpPurchaseId 采购主键
     * @return 采购
     */
    @Override
    public MrpPurchase selectMrpPurchaseByMrpPurchaseId(Long mrpPurchaseId)
    {
        return mrpPurchaseMapper.selectMrpPurchaseByMrpPurchaseId(mrpPurchaseId);
    }

    /**
     * 查询采购列表
     * 
     * @param mrpPurchase 采购
     * @return 采购
     */
    @Override
    public List<MrpPurchase> selectMrpPurchaseList(MrpPurchase mrpPurchase)
    {
        return mrpPurchaseMapper.selectMrpPurchaseList(mrpPurchase);
    }

    /**
     * 新增采购
     * 
     * @param mrpPurchase 采购
     * @return 结果
     */
    @Override
    public int insertMrpPurchase(MrpPurchase mrpPurchase)
    {
        return mrpPurchaseMapper.insertMrpPurchase(mrpPurchase);
    }

    /**
     * 修改采购
     * 
     * @param mrpPurchase 采购
     * @return 结果
     */
    @Override
    public int updateMrpPurchase(MrpPurchase mrpPurchase)
    {
        return mrpPurchaseMapper.updateMrpPurchase(mrpPurchase);
    }

    /**
     * 批量删除采购
     * 
     * @param mrpPurchaseIds 需要删除的采购主键
     * @return 结果
     */
    @Override
    public int deleteMrpPurchaseByMrpPurchaseIds(Long[] mrpPurchaseIds)
    {
        return mrpPurchaseMapper.deleteMrpPurchaseByMrpPurchaseIds(mrpPurchaseIds);
    }

    /**
     * 删除采购信息
     * 
     * @param mrpPurchaseId 采购主键
     * @return 结果
     */
    @Override
    public int deleteMrpPurchaseByMrpPurchaseId(Long mrpPurchaseId)
    {
        return mrpPurchaseMapper.deleteMrpPurchaseByMrpPurchaseId(mrpPurchaseId);
    }
}
