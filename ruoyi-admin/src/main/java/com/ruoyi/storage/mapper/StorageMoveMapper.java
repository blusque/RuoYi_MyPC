package com.ruoyi.storage.mapper;

import java.util.List;
import com.ruoyi.storage.domain.StorageMove;
import com.ruoyi.storage.domain.StorageMoveDetail;

/**
 * 移动操作Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface StorageMoveMapper 
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
     * 删除移动操作
     * 
     * @param id 移动操作主键
     * @return 结果
     */
    public int deleteStorageMoveById(Long id);

    /**
     * 批量删除移动操作
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageMoveByIds(Long[] ids);

    /**
     * 批量删除移动明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageMoveDetailByMoveIds(Long[] ids);
    
    /**
     * 批量新增移动明细
     * 
     * @param storageMoveDetailList 移动明细列表
     * @return 结果
     */
    public int batchStorageMoveDetail(List<StorageMoveDetail> storageMoveDetailList);
    

    /**
     * 通过移动操作主键删除移动明细信息
     * 
     * @param id 移动操作ID
     * @return 结果
     */
    public int deleteStorageMoveDetailByMoveId(Long id);
}
