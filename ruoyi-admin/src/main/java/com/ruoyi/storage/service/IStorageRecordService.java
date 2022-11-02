package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageRecord;

/**
 * 库存信息Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageRecordService 
{
    /**
     * 查询库存信息
     * 
     * @param id 库存信息主键
     * @return 库存信息
     */
    public StorageRecord selectStorageRecordById(Long id);

    /**
     * 查询库存信息列表
     * 
     * @param storageRecord 库存信息
     * @return 库存信息集合
     */
    public List<StorageRecord> selectStorageRecordList(StorageRecord storageRecord);

    /**
     * 新增库存信息
     * 
     * @param storageRecord 库存信息
     * @return 结果
     */
    public int insertStorageRecord(StorageRecord storageRecord);

    /**
     * 修改库存信息
     * 
     * @param storageRecord 库存信息
     * @return 结果
     */
    public int updateStorageRecord(StorageRecord storageRecord);

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的库存信息主键集合
     * @return 结果
     */
    public int deleteStorageRecordByIds(Long[] ids);

    /**
     * 删除库存信息信息
     * 
     * @param id 库存信息主键
     * @return 结果
     */
    public int deleteStorageRecordById(Long id);
}
