package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCraftMapper;
import com.ruoyi.system.domain.SysCraft;
import com.ruoyi.system.service.ISysCraftService;

/**
 * 工艺Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class SysCraftServiceImpl implements ISysCraftService 
{
    @Autowired
    private SysCraftMapper sysCraftMapper;

    /**
     * 查询工艺
     * 
     * @param id 工艺主键
     * @return 工艺
     */
    @Override
    public SysCraft selectSysCraftById(Long id)
    {
        return sysCraftMapper.selectSysCraftById(id);
    }

    /**
     * 查询工艺列表
     * 
     * @param sysCraft 工艺
     * @return 工艺
     */
    @Override
    public List<SysCraft> selectSysCraftList(SysCraft sysCraft)
    {
        return sysCraftMapper.selectSysCraftList(sysCraft);
    }

    /**
     * 新增工艺
     * 
     * @param sysCraft 工艺
     * @return 结果
     */
    @Override
    public int insertSysCraft(SysCraft sysCraft)
    {
        return sysCraftMapper.insertSysCraft(sysCraft);
    }

    /**
     * 修改工艺
     * 
     * @param sysCraft 工艺
     * @return 结果
     */
    @Override
    public int updateSysCraft(SysCraft sysCraft)
    {
        return sysCraftMapper.updateSysCraft(sysCraft);
    }

    /**
     * 批量删除工艺
     * 
     * @param ids 需要删除的工艺主键
     * @return 结果
     */
    @Override
    public int deleteSysCraftByIds(Long[] ids)
    {
        return sysCraftMapper.deleteSysCraftByIds(ids);
    }

    /**
     * 删除工艺信息
     * 
     * @param id 工艺主键
     * @return 结果
     */
    @Override
    public int deleteSysCraftById(Long id)
    {
        return sysCraftMapper.deleteSysCraftById(id);
    }
}
