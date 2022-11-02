package com.ruoyi.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.storage.mapper.StorageCheckDetailMapper;
import com.ruoyi.storage.domain.StorageCheckDetail;
import com.ruoyi.storage.service.IStorageCheckDetailService;

/**
 * 盘点明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageCheckDetailServiceImpl implements IStorageCheckDetailService 
{
    @Autowired
    private StorageCheckDetailMapper storageCheckDetailMapper;

    /**
     * 查询盘点明细
     * 
     * @param id 盘点明细主键
     * @return 盘点明细
     */
    @Override
    public StorageCheckDetail selectStorageCheckDetailById(Long id)
    {
        return storageCheckDetailMapper.selectStorageCheckDetailById(id);
    }

    /**
     * 查询盘点明细列表
     * 
     * @param storageCheckDetail 盘点明细
     * @return 盘点明细
     */
    @Override
    public List<StorageCheckDetail> selectStorageCheckDetailList(StorageCheckDetail storageCheckDetail)
    {
        return storageCheckDetailMapper.selectStorageCheckDetailList(storageCheckDetail);
    }

    /**
     * 新增盘点明细
     * 
     * @param storageCheckDetail 盘点明细
     * @return 结果
     */
    @Override
    public int insertStorageCheckDetail(StorageCheckDetail storageCheckDetail)
    {
        return storageCheckDetailMapper.insertStorageCheckDetail(storageCheckDetail);
    }

    /**
     * 修改盘点明细
     * 
     * @param storageCheckDetail 盘点明细
     * @return 结果
     */
    @Override
    public int updateStorageCheckDetail(StorageCheckDetail storageCheckDetail)
    {
        return storageCheckDetailMapper.updateStorageCheckDetail(storageCheckDetail);
    }

    /**
     * 批量删除盘点明细
     * 
     * @param ids 需要删除的盘点明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageCheckDetailByIds(Long[] ids)
    {
        return storageCheckDetailMapper.deleteStorageCheckDetailByIds(ids);
    }

    /**
     * 删除盘点明细信息
     * 
     * @param id 盘点明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageCheckDetailById(Long id)
    {
        return storageCheckDetailMapper.deleteStorageCheckDetailById(id);
    }
}
