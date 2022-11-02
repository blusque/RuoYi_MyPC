package com.ruoyi.mrp.service;

import java.util.List;
import com.ruoyi.mrp.domain.MrpProduce;

/**
 * 生产Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IMrpProduceService 
{
    /**
     * 查询生产
     * 
     * @param mrpProduceId 生产主键
     * @return 生产
     */
    public MrpProduce selectMrpProduceByMrpProduceId(Long mrpProduceId);

    /**
     * 查询生产列表
     * 
     * @param mrpProduce 生产
     * @return 生产集合
     */
    public List<MrpProduce> selectMrpProduceList(MrpProduce mrpProduce);

    /**
     * 新增生产
     * 
     * @param mrpProduce 生产
     * @return 结果
     */
    public int insertMrpProduce(MrpProduce mrpProduce);

    /**
     * 修改生产
     * 
     * @param mrpProduce 生产
     * @return 结果
     */
    public int updateMrpProduce(MrpProduce mrpProduce);

    /**
     * 批量删除生产
     * 
     * @param mrpProduceIds 需要删除的生产主键集合
     * @return 结果
     */
    public int deleteMrpProduceByMrpProduceIds(Long[] mrpProduceIds);

    /**
     * 删除生产信息
     * 
     * @param mrpProduceId 生产主键
     * @return 结果
     */
    public int deleteMrpProduceByMrpProduceId(Long mrpProduceId);
}
