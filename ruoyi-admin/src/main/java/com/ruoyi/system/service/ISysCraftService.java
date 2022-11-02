package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysCraft;

/**
 * 工艺Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ISysCraftService 
{
    /**
     * 查询工艺
     * 
     * @param id 工艺主键
     * @return 工艺
     */
    public SysCraft selectSysCraftById(Long id);

    /**
     * 查询工艺列表
     * 
     * @param sysCraft 工艺
     * @return 工艺集合
     */
    public List<SysCraft> selectSysCraftList(SysCraft sysCraft);

    /**
     * 新增工艺
     * 
     * @param sysCraft 工艺
     * @return 结果
     */
    public int insertSysCraft(SysCraft sysCraft);

    /**
     * 修改工艺
     * 
     * @param sysCraft 工艺
     * @return 结果
     */
    public int updateSysCraft(SysCraft sysCraft);

    /**
     * 批量删除工艺
     * 
     * @param ids 需要删除的工艺主键集合
     * @return 结果
     */
    public int deleteSysCraftByIds(Long[] ids);

    /**
     * 删除工艺信息
     * 
     * @param id 工艺主键
     * @return 结果
     */
    public int deleteSysCraftById(Long id);
}
