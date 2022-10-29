package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysFactory;

/**
 * 车间Mapper接口
 * 
 * @author laiyuan
 * @date 2022-10-27
 */
public interface SysFactoryMapper 
{
    /**
     * 查询车间
     * 
     * @param id 车间主键
     * @return 车间
     */
    public SysFactory selectSysFactoryById(Long id);

    /**
     * 查询车间列表
     * 
     * @param sysFactory 车间
     * @return 车间集合
     */
    public List<SysFactory> selectSysFactoryList(SysFactory sysFactory);

    /**
     * 新增车间
     * 
     * @param sysFactory 车间
     * @return 结果
     */
    public int insertSysFactory(SysFactory sysFactory);

    /**
     * 修改车间
     * 
     * @param sysFactory 车间
     * @return 结果
     */
    public int updateSysFactory(SysFactory sysFactory);

    /**
     * 删除车间
     * 
     * @param id 车间主键
     * @return 结果
     */
    public int deleteSysFactoryById(Long id);

    /**
     * 批量删除车间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFactoryByIds(Long[] ids);
}
