package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MarketCancelDetail;

/**
 * 退货明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface MarketCancelDetailMapper {
    /**
     * 查询退货明细列表
     * 
     * @param marketCancelDetail 退货明细
     * @return 退货明细集合
     */
    public List<MarketCancelDetail> selectMarketCancelDetailList(MarketCancelDetail marketCancelDetail);

    /**
     * 新增退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    public int batchMarketCancelDetail(List<MarketCancelDetail> marketCancelDetail);

    /**
     * 删除退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    public int deleteMarketCancelDetailByOrderId(Long orderId);

    /**
     * 删除退货明细
     * 
     * @param marketCancelDetail 退货明细
     * @return 结果
     */
    public int deleteMarketCancelDetailByOrderIds(Long[] orderIds);

    /**
     * 删除退货明细
     * 
     * @param id 退货明细主键
     * @return 结果
     */
    public int deleteMarketCancelDetailById(Long id);

    /**
     * 批量删除退货明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketCancelDetailByIds(Long[] ids);
}
