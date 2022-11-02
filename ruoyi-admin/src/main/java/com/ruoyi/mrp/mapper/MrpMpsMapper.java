package com.ruoyi.mrp.mapper;

import java.util.List;
import com.ruoyi.mrp.domain.MrpMps;

/**
 * MPSMapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface MrpMpsMapper 
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
     * 删除MPS
     * 
     * @param productId MPS主键
     * @return 结果
     */
    public int deleteMrpMpsByProductId(Long productId);

    /**
     * 批量删除MPS
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMrpMpsByProductIds(Long[] productIds);
}
