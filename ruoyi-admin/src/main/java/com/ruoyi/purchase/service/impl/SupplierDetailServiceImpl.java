package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.purchase.domain.SupplierDetail;
import com.ruoyi.purchase.mapper.SupplierDetailMapper;
import com.ruoyi.purchase.service.ISupplierDetailService;

/**
 * 供应商明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@Service
public class SupplierDetailServiceImpl implements ISupplierDetailService 
{
    @Autowired
    private SupplierDetailMapper supplierDetailMapper;

    /**
     * 查询供应商明细
     * 
     * @param supplierId 供应商明细主键
     * @return 供应商明细
     */
    @Override
    public SupplierDetail selectSupplierDetailBySupplierId(Long supplierId)
    {
        return supplierDetailMapper.selectSupplierDetailBySupplierId(supplierId);
    }

    /**
     * 查询供应商明细列表
     * 
     * @param supplierDetail 供应商明细
     * @return 供应商明细
     */
    @Override
    public List<SupplierDetail> selectSupplierDetailList(SupplierDetail supplierDetail)
    {
        return supplierDetailMapper.selectSupplierDetailList(supplierDetail);
    }

    /**
     * 新增供应商明细
     * 
     * @param supplierDetail 供应商明细
     * @return 结果
     */
    @Override
    public int insertSupplierDetail(SupplierDetail supplierDetail)
    {
        return supplierDetailMapper.insertSupplierDetail(supplierDetail);
    }

    /**
     * 修改供应商明细
     * 
     * @param supplierDetail 供应商明细
     * @return 结果
     */
    @Override
    public int updateSupplierDetail(SupplierDetail supplierDetail)
    {
        return supplierDetailMapper.updateSupplierDetail(supplierDetail);
    }

    /**
     * 批量删除供应商明细
     * 
     * @param supplierIds 需要删除的供应商明细主键
     * @return 结果
     */
    @Override
    public int deleteSupplierDetailBySupplierIds(Long[] supplierIds)
    {
        return supplierDetailMapper.deleteSupplierDetailBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商明细信息
     * 
     * @param supplierId 供应商明细主键
     * @return 结果
     */
    @Override
    public int deleteSupplierDetailBySupplierId(Long supplierId)
    {
        return supplierDetailMapper.deleteSupplierDetailBySupplierId(supplierId);
    }
}
