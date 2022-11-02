package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysMaterialMapper;
import com.ruoyi.system.domain.SysMaterial;
import com.ruoyi.system.service.ISysMaterialService;

/**
 * 物料管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@Service
public class SysMaterialServiceImpl implements ISysMaterialService 
{
    @Autowired
    private SysMaterialMapper sysMaterialMapper;

    /**
     * 查询物料管理
     * 
     * @param id 物料管理主键
     * @return 物料管理
     */
    @Override
    public SysMaterial selectSysMaterialById(Long id)
    {
        return sysMaterialMapper.selectSysMaterialById(id);
    }

    /**
     * 查询物料管理列表
     * 
     * @param sysMaterial 物料管理
     * @return 物料管理
     */
    @Override
    public List<SysMaterial> selectSysMaterialList(SysMaterial sysMaterial)
    {
        return sysMaterialMapper.selectSysMaterialList(sysMaterial);
    }

    /**
     * 新增物料管理
     * 
     * @param sysMaterial 物料管理
     * @return 结果
     */
    @Override
    public int insertSysMaterial(SysMaterial sysMaterial)
    {
        return sysMaterialMapper.insertSysMaterial(sysMaterial);
    }

    /**
     * 修改物料管理
     * 
     * @param sysMaterial 物料管理
     * @return 结果
     */
    @Override
    public int updateSysMaterial(SysMaterial sysMaterial)
    {
        return sysMaterialMapper.updateSysMaterial(sysMaterial);
    }

    /**
     * 批量删除物料管理
     * 
     * @param ids 需要删除的物料管理主键
     * @return 结果
     */
    @Override
    public int deleteSysMaterialByIds(Long[] ids)
    {
        return sysMaterialMapper.deleteSysMaterialByIds(ids);
    }

    /**
     * 删除物料管理信息
     * 
     * @param id 物料管理主键
     * @return 结果
     */
    @Override
    public int deleteSysMaterialById(Long id)
    {
        return sysMaterialMapper.deleteSysMaterialById(id);
    }
}
