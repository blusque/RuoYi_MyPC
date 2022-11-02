package com.ruoyi.mrp.service;

import java.util.List;
import com.ruoyi.mrp.domain.MrpMps;

/**
 * MPSService接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IMrpMpsService 
{
    /**
     * 查询MPS
     * 
     * @param productId MPS主键
     * @return MPS
     */
    public MrpMps selectMrpMpsByProductId(Long productId);

    /**
     * 查询MPS列表
     * 
     * @param mrpMps MPS
     * @return MPS集合
     */
    public List<MrpMps> selectMrpMpsList(MrpMps mrpMps);

    /**
     * 新增MPS
     * 
     * @param mrpMps MPS
     * @return 结果
     */
    public int insertMrpMps(MrpMps mrpMps);

    /**
     * 修改MPS
     * 
     * @param mrpMps MPS
     * @return 结果
     */
    public int updateMrpMps(MrpMps mrpMps);

    /**
     * 批量删除MPS
     * 
     * @param productIds 需要删除的MPS主键集合
     * @return 结果
     */
    public int deleteMrpMpsByProductIds(Long[] productIds);

    /**
     * 删除MPS信息
     * 
     * @param productId MPS主键
     * @return 结果
     */
    public int deleteMrpMpsByProductId(Long productId);
}
