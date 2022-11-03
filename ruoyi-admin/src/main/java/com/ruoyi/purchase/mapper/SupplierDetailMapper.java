package com.ruoyi.purchase.mapper;

import java.util.List;

import com.ruoyi.purchase.domain.SupplierDetail;

/**
 * 供应商明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface SupplierDetailMapper 
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
     * 删除供应商明细
     * 
     * @param supplierId 供应商明细主键
     * @return 结果
     */
    public int deleteSupplierDetailBySupplierId(Long supplierId);

    /**
     * 批量删除供应商明细
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierDetailBySupplierIds(Long[] supplierIds);
}
