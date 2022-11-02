package com.ruoyi.mrp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mrp.mapper.MrpMpsMapper;
import com.ruoyi.mrp.domain.MrpMps;
import com.ruoyi.mrp.service.IMrpMpsService;

/**
 * MPSService业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class MrpMpsServiceImpl implements IMrpMpsService 
{
    @Autowired
    private MrpMpsMapper mrpMpsMapper;

    /**
     * 查询MPS
     * 
     * @param productId MPS主键
     * @return MPS
     */
    @Override
    public MrpMps selectMrpMpsByProductId(Long productId)
    {
        return mrpMpsMapper.selectMrpMpsByProductId(productId);
    }

    /**
     * 查询MPS列表
     * 
     * @param mrpMps MPS
     * @return MPS
     */
    @Override
    public List<MrpMps> selectMrpMpsList(MrpMps mrpMps)
    {
        return mrpMpsMapper.selectMrpMpsList(mrpMps);
    }

    /**
     * 新增MPS
     * 
     * @param mrpMps MPS
     * @return 结果
     */
    @Override
    public int insertMrpMps(MrpMps mrpMps)
    {
        return mrpMpsMapper.insertMrpMps(mrpMps);
    }

    /**
     * 修改MPS
     * 
     * @param mrpMps MPS
     * @return 结果
     */
    @Override
    public int updateMrpMps(MrpMps mrpMps)
    {
        return mrpMpsMapper.updateMrpMps(mrpMps);
    }

    /**
     * 批量删除MPS
     * 
     * @param productIds 需要删除的MPS主键
     * @return 结果
     */
    @Override
    public int deleteMrpMpsByProductIds(Long[] productIds)
    {
        return mrpMpsMapper.deleteMrpMpsByProductIds(productIds);
    }

    /**
     * 删除MPS信息
     * 
     * @param productId MPS主键
     * @return 结果
     */
    @Override
    public int deleteMrpMpsByProductId(Long productId)
    {
        return mrpMpsMapper.deleteMrpMpsByProductId(productId);
    }
}
