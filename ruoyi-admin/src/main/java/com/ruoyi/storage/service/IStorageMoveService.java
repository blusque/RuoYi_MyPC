package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageMove;

/**
 * 移动操作Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageMoveService 
{
    /**
     * 查询移动操作
     * 
     * @param id 移动操作主键
     * @return 移动操作
     */
    public StorageMove selectStorageMoveById(Long id);

    /**
     * 查询移动操作列表
     * 
     * @param storageMove 移动操作
     * @return 移动操作集合
     */
    public List<StorageMove> selectStorageMoveList(StorageMove storageMove);

    /**
     * 新增移动操作
     * 
     * @param storageMove 移动操作
     * @return 结果
     */
    public int insertStorageMove(StorageMove storageMove);

    /**
     * 修改移动操作
     * 
     * @param storageMove 移动操作
     * @return 结果
     */
    public int updateStorageMove(StorageMove storageMove);

    /**
     * 批量删除移动操作
     * 
     * @param ids 需要删除的移动操作主键集合
     * @return 结果
     */
    public int deleteStorageMoveByIds(Long[] ids);

    /**
     * 删除移动操作信息
     * 
     * @param id 移动操作主键
     * @return 结果
     */
    public int deleteStorageMoveById(Long id);
}
