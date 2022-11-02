package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysFactory;

/**
 * 车间管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface SysFactoryMapper 
{
    /**
     * 查询车间管理
     * 
     * @param id 车间管理主键
     * @return 车间管理
     */
    public SysFactory selectSysFactoryById(Long id);

    /**
     * 查询车间管理列表
     * 
     * @param sysFactory 车间管理
     * @return 车间管理集合
     */
    public List<SysFactory> selectSysFactoryList(SysFactory sysFactory);

    /**
     * 新增车间管理
     * 
     * @param sysFactory 车间管理
     * @return 结果
     */
    public int insertSysFactory(SysFactory sysFactory);

    /**
     * 修改车间管理
     * 
     * @param sysFactory 车间管理
     * @return 结果
     */
    public int updateSysFactory(SysFactory sysFactory);

    /**
     * 删除车间管理
     * 
     * @param id 车间管理主键
     * @return 结果
     */
    public int deleteSysFactoryById(Long id);

    /**
     * 批量删除车间管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFactoryByIds(Long[] ids);
}
