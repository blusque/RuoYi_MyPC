package com.ruoyi.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.storage.mapper.StorageOutinDetailMapper;
import com.ruoyi.storage.domain.StorageOutinDetail;
import com.ruoyi.storage.service.IStorageOutinDetailService;

/**
 * 出入明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageOutinDetailServiceImpl implements IStorageOutinDetailService 
{
    @Autowired
    private StorageOutinDetailMapper storageOutinDetailMapper;

    /**
     * 查询出入明细
     * 
     * @param id 出入明细主键
     * @return 出入明细
     */
    @Override
    public StorageOutinDetail selectStorageOutinDetailById(Long id)
    {
        return storageOutinDetailMapper.selectStorageOutinDetailById(id);
    }

    /**
     * 查询出入明细列表
     * 
     * @param storageOutinDetail 出入明细
     * @return 出入明细
     */
    @Override
    public List<StorageOutinDetail> selectStorageOutinDetailList(StorageOutinDetail storageOutinDetail)
    {
        return storageOutinDetailMapper.selectStorageOutinDetailList(storageOutinDetail);
    }

    /**
     * 新增出入明细
     * 
     * @param storageOutinDetail 出入明细
     * @return 结果
     */
    @Override
    public int insertStorageOutinDetail(StorageOutinDetail storageOutinDetail)
    {
        return storageOutinDetailMapper.insertStorageOutinDetail(storageOutinDetail);
    }

    /**
     * 修改出入明细
     * 
     * @param storageOutinDetail 出入明细
     * @return 结果
     */
    @Override
    public int updateStorageOutinDetail(StorageOutinDetail storageOutinDetail)
    {
        return storageOutinDetailMapper.updateStorageOutinDetail(storageOutinDetail);
    }

    /**
     * 批量删除出入明细
     * 
     * @param ids 需要删除的出入明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageOutinDetailByIds(Long[] ids)
    {
        return storageOutinDetailMapper.deleteStorageOutinDetailByIds(ids);
    }

    /**
     * 删除出入明细信息
     * 
     * @param id 出入明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageOutinDetailById(Long id)
    {
        return storageOutinDetailMapper.deleteStorageOutinDetailById(id);
    }
}
