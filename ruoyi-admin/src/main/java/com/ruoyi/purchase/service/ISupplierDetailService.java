package com.ruoyi.purchase.service;

import java.util.List;

import com.ruoyi.purchase.domain.SupplierDetail;

/**
 * 供应商明细Service接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface ISupplierDetailService 
{
    /**
     * 查询供应商明细
     * 
     * @param supplierId 供应商明细主键
     * @return 供应商明细
     */
    public SupplierDetail selectSupplierDetailBySupplierId(Long supplierId);

    /**
     * 查询供应商明细列表
     * 
     * @param supplierDetail 供应商明细
     * @return 供应商明细集合
     */
    public List<SupplierDetail> selectSupplierDetailList(SupplierDetail supplierDetail);

    /**
     * 新增供应商明细
     * 
     * @param supplierDetail 供应商明细
     * @return 结果
     */
    public int insertSupplierDetail(SupplierDetail supplierDetail);

    /**
     * 修改供应商明细
     * 
     * @param supplierDetail 供应商明细
     * @return 结果
     */
    public int updateSupplierDetail(SupplierDetail supplierDetail);

    /**
     * 批量删除供应商明细
     * 
     * @param supplierIds 需要删除的供应商明细主键集合
     * @return 结果
     */
    public int deleteSupplierDetailBySupplierIds(Long[] supplierIds);

    /**
     * 删除供应商明细信息
     * 
     * @param supplierId 供应商明细主键
     * @return 结果
     */
    public int deleteSupplierDetailBySupplierId(Long supplierId);
}
