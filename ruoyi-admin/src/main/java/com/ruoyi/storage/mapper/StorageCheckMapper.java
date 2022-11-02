package com.ruoyi.storage.mapper;

import java.util.List;
import com.ruoyi.storage.domain.StorageCheck;
import com.ruoyi.storage.domain.StorageCheckDetail;

/**
 * 盘点操作Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface StorageCheckMapper 
{
    /**
     * 查询盘点操作
     * 
     * @param id 盘点操作主键
     * @return 盘点操作
     */
    public StorageCheck selectStorageCheckById(Long id);

    /**
     * 查询盘点操作列表
     * 
     * @param storageCheck 盘点操作
     * @return 盘点操作集合
     */
    public List<StorageCheck> selectStorageCheckList(StorageCheck storageCheck);

    /**
     * 新增盘点操作
     * 
     * @param storageCheck 盘点操作
     * @return 结果
     */
    public int insertStorageCheck(StorageCheck storageCheck);

    /**
     * 修改盘点操作
     * 
     * @param storageCheck 盘点操作
     * @return 结果
     */
    public int updateStorageCheck(StorageCheck storageCheck);

    /**
     * 删除盘点操作
     * 
     * @param id 盘点操作主键
     * @return 结果
     */
    public int deleteStorageCheckById(Long id);

    /**
     * 批量删除盘点操作
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageCheckByIds(Long[] ids);

    /**
     * 批量删除盘点明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageCheckDetailByCheckIds(Long[] ids);
    
    /**
     * 批量新增盘点明细
     * 
     * @param storageCheckDetailList 盘点明细列表
     * @return 结果
     */
    public int batchStorageCheckDetail(List<StorageCheckDetail> storageCheckDetailList);
    

    /**
     * 通过盘点操作主键删除盘点明细信息
     * 
     * @param id 盘点操作ID
     * @return 结果
     */
    public int deleteStorageCheckDetailByCheckId(Long id);
}
