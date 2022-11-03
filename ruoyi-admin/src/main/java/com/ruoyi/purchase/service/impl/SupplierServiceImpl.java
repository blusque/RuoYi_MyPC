package com.ruoyi.purchase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.purchase.domain.Supplier;
import com.ruoyi.purchase.mapper.SupplierMapper;
import com.ruoyi.purchase.service.ISupplierService;

/**
 * 供应商Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询供应商
     * 
     * @param id 供应商主键
     * @return 供应商
     */
    @Override
    public Supplier selectSupplierById(Long id)
    {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询供应商列表
     * 
     * @param supplier 供应商
     * @return 供应商
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(Long[] ids)
    {
        return supplierMapper.deleteSupplierByIds(ids);
    }

    /**
     * 删除供应商信息
     * 
     * @param id 供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplierById(Long id)
    {
        return supplierMapper.deleteSupplierById(id);
    }
}
