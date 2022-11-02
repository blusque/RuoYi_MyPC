package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCraft;

/**
 * 工艺管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface SysCraftMapper 
{
    /**
     * 查询工艺管理
     * 
     * @param id 工艺管理主键
     * @return 工艺管理
     */
    public SysCraft selectSysCraftById(Long id);

    /**
     * 查询工艺管理列表
     * 
     * @param sysCraft 工艺管理
     * @return 工艺管理集合
     */
    public List<SysCraft> selectSysCraftList(SysCraft sysCraft);

    /**
     * 新增工艺管理
     * 
     * @param sysCraft 工艺管理
     * @return 结果
     */
    public int insertSysCraft(SysCraft sysCraft);

    /**
     * 修改工艺管理
     * 
     * @param sysCraft 工艺管理
     * @return 结果
     */
    public int updateSysCraft(SysCraft sysCraft);

    /**
     * 删除工艺管理
     * 
     * @param id 工艺管理主键
     * @return 结果
     */
    public int deleteSysCraftById(Long id);

    /**
     * 批量删除工艺管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCraftByIds(Long[] ids);
}
