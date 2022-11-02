package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysStorageMapper;
import com.ruoyi.system.domain.SysStorage;
import com.ruoyi.system.service.ISysStorageService;

/**
 * 仓库管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@Service
public class SysStorageServiceImpl implements ISysStorageService 
{
    @Autowired
    private SysStorageMapper sysStorageMapper;

    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理主键
     * @return 仓库管理
     */
    @Override
    public SysStorage selectSysStorageById(Long id)
    {
        return sysStorageMapper.selectSysStorageById(id);
    }

    /**
     * 查询仓库管理列表
     * 
     * @param sysStorage 仓库管理
     * @return 仓库管理
     */
    @Override
    public List<SysStorage> selectSysStorageList(SysStorage sysStorage)
    {
        return sysStorageMapper.selectSysStorageList(sysStorage);
    }

    /**
     * 新增仓库管理
     * 
     * @param sysStorage 仓库管理
     * @return 结果
     */
    @Override
    public int insertSysStorage(SysStorage sysStorage)
    {
        return sysStorageMapper.insertSysStorage(sysStorage);
    }

    /**
     * 修改仓库管理
     * 
     * @param sysStorage 仓库管理
     * @return 结果
     */
    @Override
    public int updateSysStorage(SysStorage sysStorage)
    {
        return sysStorageMapper.updateSysStorage(sysStorage);
    }

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的仓库管理主键
     * @return 结果
     */
    @Override
    public int deleteSysStorageByIds(Long[] ids)
    {
        return sysStorageMapper.deleteSysStorageByIds(ids);
    }

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理主键
     * @return 结果
     */
    @Override
    public int deleteSysStorageById(Long id)
    {
        return sysStorageMapper.deleteSysStorageById(id);
    }
}
