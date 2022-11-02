package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageOutin;

/**
 * 出入操作Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageOutinService 
{
    /**
     * 查询出入操作
     * 
     * @param id 出入操作主键
     * @return 出入操作
     */
    public StorageOutin selectStorageOutinById(Long id);

    /**
     * 查询出入操作列表
     * 
     * @param storageOutin 出入操作
     * @return 出入操作集合
     */
    public List<StorageOutin> selectStorageOutinList(StorageOutin storageOutin);

    /**
     * 新增出入操作
     * 
     * @param storageOutin 出入操作
     * @return 结果
     */
    public int insertStorageOutin(StorageOutin storageOutin);

    /**
     * 修改出入操作
     * 
     * @param storageOutin 出入操作
     * @return 结果
     */
    public int updateStorageOutin(StorageOutin storageOutin);

    /**
     * 批量删除出入操作
     * 
     * @param ids 需要删除的出入操作主键集合
     * @return 结果
     */
    public int deleteStorageOutinByIds(Long[] ids);

    /**
     * 删除出入操作信息
     * 
     * @param id 出入操作主键
     * @return 结果
     */
    public int deleteStorageOutinById(Long id);
}
