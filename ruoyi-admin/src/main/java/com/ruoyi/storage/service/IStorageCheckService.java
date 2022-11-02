package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageCheck;

/**
 * 盘点操作Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageCheckService 
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
     * 批量删除盘点操作
     * 
     * @param ids 需要删除的盘点操作主键集合
     * @return 结果
     */
    public int deleteStorageCheckByIds(Long[] ids);

    /**
     * 删除盘点操作信息
     * 
     * @param id 盘点操作主键
     * @return 结果
     */
    public int deleteStorageCheckById(Long id);
}
