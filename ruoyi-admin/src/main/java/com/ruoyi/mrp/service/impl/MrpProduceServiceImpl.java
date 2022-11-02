package com.ruoyi.mrp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mrp.mapper.MrpProduceMapper;
import com.ruoyi.mrp.domain.MrpProduce;
import com.ruoyi.mrp.service.IMrpProduceService;

/**
 * 生产Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class MrpProduceServiceImpl implements IMrpProduceService 
{
    @Autowired
    private MrpProduceMapper mrpProduceMapper;

    /**
     * 查询生产
     * 
     * @param mrpProduceId 生产主键
     * @return 生产
     */
    @Override
    public MrpProduce selectMrpProduceByMrpProduceId(Long mrpProduceId)
    {
        return mrpProduceMapper.selectMrpProduceByMrpProduceId(mrpProduceId);
    }

    /**
     * 查询生产列表
     * 
     * @param mrpProduce 生产
     * @return 生产
     */
    @Override
    public List<MrpProduce> selectMrpProduceList(MrpProduce mrpProduce)
    {
        return mrpProduceMapper.selectMrpProduceList(mrpProduce);
    }

    /**
     * 新增生产
     * 
     * @param mrpProduce 生产
     * @return 结果
     */
    @Override
    public int insertMrpProduce(MrpProduce mrpProduce)
    {
        return mrpProduceMapper.insertMrpProduce(mrpProduce);
    }

    /**
     * 修改生产
     * 
     * @param mrpProduce 生产
     * @return 结果
     */
    @Override
    public int updateMrpProduce(MrpProduce mrpProduce)
    {
        return mrpProduceMapper.updateMrpProduce(mrpProduce);
    }

    /**
     * 批量删除生产
     * 
     * @param mrpProduceIds 需要删除的生产主键
     * @return 结果
     */
    @Override
    public int deleteMrpProduceByMrpProduceIds(Long[] mrpProduceIds)
    {
        return mrpProduceMapper.deleteMrpProduceByMrpProduceIds(mrpProduceIds);
    }

    /**
     * 删除生产信息
     * 
     * @param mrpProduceId 生产主键
     * @return 结果
     */
    @Override
    public int deleteMrpProduceByMrpProduceId(Long mrpProduceId)
    {
        return mrpProduceMapper.deleteMrpProduceByMrpProduceId(mrpProduceId);
    }
}
