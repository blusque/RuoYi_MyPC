package com.ruoyi.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.storage.mapper.StorageMoveDetailMapper;
import com.ruoyi.storage.domain.StorageMoveDetail;
import com.ruoyi.storage.service.IStorageMoveDetailService;

/**
 * 移动明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageMoveDetailServiceImpl implements IStorageMoveDetailService 
{
    @Autowired
    private StorageMoveDetailMapper storageMoveDetailMapper;

    /**
     * 查询移动明细
     * 
     * @param id 移动明细主键
     * @return 移动明细
     */
    @Override
    public StorageMoveDetail selectStorageMoveDetailById(Long id)
    {
        return storageMoveDetailMapper.selectStorageMoveDetailById(id);
    }

    /**
     * 查询移动明细列表
     * 
     * @param storageMoveDetail 移动明细
     * @return 移动明细
     */
    @Override
    public List<StorageMoveDetail> selectStorageMoveDetailList(StorageMoveDetail storageMoveDetail)
    {
        return storageMoveDetailMapper.selectStorageMoveDetailList(storageMoveDetail);
    }

    /**
     * 新增移动明细
     * 
     * @param storageMoveDetail 移动明细
     * @return 结果
     */
    @Override
    public int insertStorageMoveDetail(StorageMoveDetail storageMoveDetail)
    {
        return storageMoveDetailMapper.insertStorageMoveDetail(storageMoveDetail);
    }

    /**
     * 修改移动明细
     * 
     * @param storageMoveDetail 移动明细
     * @return 结果
     */
    @Override
    public int updateStorageMoveDetail(StorageMoveDetail storageMoveDetail)
    {
        return storageMoveDetailMapper.updateStorageMoveDetail(storageMoveDetail);
    }

    /**
     * 批量删除移动明细
     * 
     * @param ids 需要删除的移动明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageMoveDetailByIds(Long[] ids)
    {
        return storageMoveDetailMapper.deleteStorageMoveDetailByIds(ids);
    }

    /**
     * 删除移动明细信息
     * 
     * @param id 移动明细主键
     * @return 结果
     */
    @Override
    public int deleteStorageMoveDetailById(Long id)
    {
        return storageMoveDetailMapper.deleteStorageMoveDetailById(id);
    }
}
