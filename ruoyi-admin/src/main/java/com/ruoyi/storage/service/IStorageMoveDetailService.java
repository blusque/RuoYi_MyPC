package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageMoveDetail;

/**
 * 移动明细Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageMoveDetailService 
{
    /**
     * 查询移动明细
     * 
     * @param id 移动明细主键
     * @return 移动明细
     */
    public StorageMoveDetail selectStorageMoveDetailById(Long id);

    /**
     * 查询移动明细列表
     * 
     * @param storageMoveDetail 移动明细
     * @return 移动明细集合
     */
    public List<StorageMoveDetail> selectStorageMoveDetailList(StorageMoveDetail storageMoveDetail);

    /**
     * 新增移动明细
     * 
     * @param storageMoveDetail 移动明细
     * @return 结果
     */
    public int insertStorageMoveDetail(StorageMoveDetail storageMoveDetail);

    /**
     * 修改移动明细
     * 
     * @param storageMoveDetail 移动明细
     * @return 结果
     */
    public int updateStorageMoveDetail(StorageMoveDetail storageMoveDetail);

    /**
     * 批量删除移动明细
     * 
     * @param ids 需要删除的移动明细主键集合
     * @return 结果
     */
    public int deleteStorageMoveDetailByIds(Long[] ids);

    /**
     * 删除移动明细信息
     * 
     * @param id 移动明细主键
     * @return 结果
     */
    public int deleteStorageMoveDetailById(Long id);
}
