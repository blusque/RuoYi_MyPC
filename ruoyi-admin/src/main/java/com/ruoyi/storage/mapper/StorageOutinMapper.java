package com.ruoyi.storage.mapper;

import java.util.List;
import com.ruoyi.storage.domain.StorageOutin;
import com.ruoyi.storage.domain.StorageOutinDetail;

/**
 * 出入操作Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface StorageOutinMapper 
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
     * 删除出入操作
     * 
     * @param id 出入操作主键
     * @return 结果
     */
    public int deleteStorageOutinById(Long id);

    /**
     * 批量删除出入操作
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageOutinByIds(Long[] ids);

    /**
     * 批量删除出入明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageOutinDetailByOutinIds(Long[] ids);
    
    /**
     * 批量新增出入明细
     * 
     * @param storageOutinDetailList 出入明细列表
     * @return 结果
     */
    public int batchStorageOutinDetail(List<StorageOutinDetail> storageOutinDetailList);
    

    /**
     * 通过出入操作主键删除出入明细信息
     * 
     * @param id 出入操作ID
     * @return 结果
     */
    public int deleteStorageOutinDetailByOutinId(Long id);
}
