package com.ruoyi.storage.mapper;

import java.util.List;

import com.ruoyi.storage.domain.StorageOutinDetail;
import com.ruoyi.storage.domain.StorageRecord;

/**
 * 库存信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface StorageRecordMapper 
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
     * 删除库存信息
     * 
     * @param id 库存信息主键
     * @return 结果
     */
    public int deleteStorageRecordById(Long id);

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageRecordByIds(Long[] ids);

    /**
     * 批量新增出入明细
     *
     * @param storageRecord 出入明细列表
     * @return 结果
     */
    public int batchStorageRecord(StorageRecord storageRecord);

    /**
     * 修改库存信息
     *
     * @param storageRecord 库存信息
     * @return 结果
     */
    public int updateStorageRecord1(StorageRecord storageRecord);

    /**
     * 删除库存信息
     *
     * @param id 库存信息主键
     * @return 结果
     */
    public int deleteStorageRecordBynum0(StorageRecord storageRecord);
}
