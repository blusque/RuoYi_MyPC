package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysStorage;

/**
 * 仓库管理Service接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface ISysStorageService 
{
    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理主键
     * @return 仓库管理
     */
    public SysStorage selectSysStorageById(Long id);

    /**
     * 查询仓库管理列表
     * 
     * @param sysStorage 仓库管理
     * @return 仓库管理集合
     */
    public List<SysStorage> selectSysStorageList(SysStorage sysStorage);

    /**
     * 新增仓库管理
     * 
     * @param sysStorage 仓库管理
     * @return 结果
     */
    public int insertSysStorage(SysStorage sysStorage);

    /**
     * 修改仓库管理
     * 
     * @param sysStorage 仓库管理
     * @return 结果
     */
    public int updateSysStorage(SysStorage sysStorage);

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的仓库管理主键集合
     * @return 结果
     */
    public int deleteSysStorageByIds(Long[] ids);

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理主键
     * @return 结果
     */
    public int deleteSysStorageById(Long id);
}
