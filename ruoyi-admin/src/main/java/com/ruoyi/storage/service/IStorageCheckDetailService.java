package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageCheckDetail;

/**
 * 盘点明细Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageCheckDetailService 
{
    /**
     * 查询盘点明细
     * 
     * @param id 盘点明细主键
     * @return 盘点明细
     */
    public StorageCheckDetail selectStorageCheckDetailById(Long id);

    /**
     * 查询盘点明细列表
     * 
     * @param storageCheckDetail 盘点明细
     * @return 盘点明细集合
     */
    public List<StorageCheckDetail> selectStorageCheckDetailList(StorageCheckDetail storageCheckDetail);

    /**
     * 新增盘点明细
     * 
     * @param storageCheckDetail 盘点明细
     * @return 结果
     */
    public int insertStorageCheckDetail(StorageCheckDetail storageCheckDetail);

    /**
     * 修改盘点明细
     * 
     * @param storageCheckDetail 盘点明细
     * @return 结果
     */
    public int updateStorageCheckDetail(StorageCheckDetail storageCheckDetail);

    /**
     * 批量删除盘点明细
     * 
     * @param ids 需要删除的盘点明细主键集合
     * @return 结果
     */
    public int deleteStorageCheckDetailByIds(Long[] ids);

    /**
     * 删除盘点明细信息
     * 
     * @param id 盘点明细主键
     * @return 结果
     */
    public int deleteStorageCheckDetailById(Long id);
}
