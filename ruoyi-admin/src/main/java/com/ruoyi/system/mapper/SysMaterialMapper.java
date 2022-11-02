package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysMaterial;

/**
 * 物料管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface SysMaterialMapper 
{
    /**
     * 查询物料管理
     * 
     * @param id 物料管理主键
     * @return 物料管理
     */
    public SysMaterial selectSysMaterialById(Long id);

    /**
     * 查询物料管理列表
     * 
     * @param sysMaterial 物料管理
     * @return 物料管理集合
     */
    public List<SysMaterial> selectSysMaterialList(SysMaterial sysMaterial);

    /**
     * 新增物料管理
     * 
     * @param sysMaterial 物料管理
     * @return 结果
     */
    public int insertSysMaterial(SysMaterial sysMaterial);

    /**
     * 修改物料管理
     * 
     * @param sysMaterial 物料管理
     * @return 结果
     */
    public int updateSysMaterial(SysMaterial sysMaterial);

    /**
     * 删除物料管理
     * 
     * @param id 物料管理主键
     * @return 结果
     */
    public int deleteSysMaterialById(Long id);

    /**
     * 批量删除物料管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMaterialByIds(Long[] ids);
}
