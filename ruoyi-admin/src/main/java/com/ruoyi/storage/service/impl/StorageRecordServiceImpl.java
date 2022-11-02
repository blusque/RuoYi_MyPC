package com.ruoyi.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.storage.mapper.StorageRecordMapper;
import com.ruoyi.storage.domain.StorageRecord;
import com.ruoyi.storage.service.IStorageRecordService;

/**
 * 库存信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageRecordServiceImpl implements IStorageRecordService 
{
    @Autowired
    private StorageRecordMapper storageRecordMapper;

    /**
     * 查询库存信息
     * 
     * @param id 库存信息主键
     * @return 库存信息
     */
    @Override
    public StorageRecord selectStorageRecordById(Long id)
    {
        return storageRecordMapper.selectStorageRecordById(id);
    }

    /**
     * 查询库存信息列表
     * 
     * @param storageRecord 库存信息
     * @return 库存信息
     */
    @Override
    public List<StorageRecord> selectStorageRecordList(StorageRecord storageRecord)
    {
        return storageRecordMapper.selectStorageRecordList(storageRecord);
    }

    /**
     * 新增库存信息
     * 
     * @param storageRecord 库存信息
     * @return 结果
     */
    @Override
    public int insertStorageRecord(StorageRecord storageRecord)
    {
        return storageRecordMapper.insertStorageRecord(storageRecord);
    }

    /**
     * 修改库存信息
     * 
     * @param storageRecord 库存信息
     * @return 结果
     */
    @Override
    public int updateStorageRecord(StorageRecord storageRecord)
    {
        return storageRecordMapper.updateStorageRecord(storageRecord);
    }

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的库存信息主键
     * @return 结果
     */
    @Override
    public int deleteStorageRecordByIds(Long[] ids)
    {
        return storageRecordMapper.deleteStorageRecordByIds(ids);
    }

    /**
     * 删除库存信息信息
     * 
     * @param id 库存信息主键
     * @return 结果
     */
    @Override
    public int deleteStorageRecordById(Long id)
    {
        return storageRecordMapper.deleteStorageRecordById(id);
    }
}
