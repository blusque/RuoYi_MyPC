package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.StorageOutinDetail;

/**
 * 出入明细Service接口
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
public interface IStorageOutinDetailService 
{
    /**
     * 查询出入明细
     * 
     * @param id 出入明细主键
     * @return 出入明细
     */
    public StorageOutinDetail selectStorageOutinDetailById(Long id);

    /**
     * 查询出入明细列表
     * 
     * @param storageOutinDetail 出入明细
     * @return 出入明细集合
     */
    public List<StorageOutinDetail> selectStorageOutinDetailList(StorageOutinDetail storageOutinDetail);

    /**
     * 新增出入明细
     * 
     * @param storageOutinDetail 出入明细
     * @return 结果
     */
    public int insertStorageOutinDetail(StorageOutinDetail storageOutinDetail);

    /**
     * 修改出入明细
     * 
     * @param storageOutinDetail 出入明细
     * @return 结果
     */
    public int updateStorageOutinDetail(StorageOutinDetail storageOutinDetail);

    /**
     * 批量删除出入明细
     * 
     * @param ids 需要删除的出入明细主键集合
     * @return 结果
     */
    public int deleteStorageOutinDetailByIds(Long[] ids);

    /**
     * 删除出入明细信息
     * 
     * @param id 出入明细主键
     * @return 结果
     */
    public int deleteStorageOutinDetailById(Long id);
}
