package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFactoryMapper;
import com.ruoyi.system.domain.SysFactory;
import com.ruoyi.system.service.ISysFactoryService;

/**
 * 车间Service业务层处理
 * 
 * @author laiyuan
 * @date 2022-10-27
 */
@Service
public class SysFactoryServiceImpl implements ISysFactoryService 
{
    @Autowired
    private SysFactoryMapper sysFactoryMapper;

    /**
     * 查询车间
     * 
     * @param id 车间主键
     * @return 车间
     */
    @Override
    public SysFactory selectSysFactoryById(Long id)
    {
        return sysFactoryMapper.selectSysFactoryById(id);
    }

    /**
     * 查询车间列表
     * 
     * @param sysFactory 车间
     * @return 车间
     */
    @Override
    public List<SysFactory> selectSysFactoryList(SysFactory sysFactory)
    {
        return sysFactoryMapper.selectSysFactoryList(sysFactory);
    }

    /**
     * 新增车间
     * 
     * @param sysFactory 车间
     * @return 结果
     */
    @Override
    public int insertSysFactory(SysFactory sysFactory)
    {
        return sysFactoryMapper.insertSysFactory(sysFactory);
    }

    /**
     * 修改车间
     * 
     * @param sysFactory 车间
     * @return 结果
     */
    @Override
    public int updateSysFactory(SysFactory sysFactory)
    {
        return sysFactoryMapper.updateSysFactory(sysFactory);
    }

    /**
     * 批量删除车间
     * 
     * @param ids 需要删除的车间主键
     * @return 结果
     */
    @Override
    public int deleteSysFactoryByIds(Long[] ids)
    {
        return sysFactoryMapper.deleteSysFactoryByIds(ids);
    }

    /**
     * 删除车间信息
     * 
     * @param id 车间主键
     * @return 结果
     */
    @Override
    public int deleteSysFactoryById(Long id)
    {
        return sysFactoryMapper.deleteSysFactoryById(id);
    }
}
