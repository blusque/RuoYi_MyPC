package com.ruoyi.purchase.mapper;

import java.util.List;

import com.ruoyi.purchase.domain.Supplier;

/**
 * 供应商Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface SupplierMapper 
{
    /**
     * 查询供应商
     * 
     * @param id 供应商主键
     * @return 供应商
     */
    public Supplier selectSupplierById(Long id);

    /**
     * 查询供应商列表
     * 
     * @param supplier 供应商
     * @return 供应商集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 删除供应商
     * 
     * @param id 供应商主键
     * @return 结果
     */
    public int deleteSupplierById(Long id);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierByIds(Long[] ids);
}
