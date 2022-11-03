package com.ruoyi.sc.mapper;

import java.util.List;
import com.ruoyi.sc.domain.ProductionOfWorkTickets;

/**
 * 生产工票Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ProductionOfWorkTicketsMapper 
{
    /**
     * 查询生产工票
     * 
     * @param mcnum 生产工票主键
     * @return 生产工票
     */
    public ProductionOfWorkTickets selectProductionOfWorkTicketsByMcnum(Long mcnum);

    /**
     * 查询生产工票列表
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 生产工票集合
     */
    public List<ProductionOfWorkTickets> selectProductionOfWorkTicketsList(ProductionOfWorkTickets productionOfWorkTickets);

    /**
     * 新增生产工票
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 结果
     */
    public int insertProductionOfWorkTickets(ProductionOfWorkTickets productionOfWorkTickets);

    /**
     * 修改生产工票
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 结果
     */
    public int updateProductionOfWorkTickets(ProductionOfWorkTickets productionOfWorkTickets);

    /**
     * 删除生产工票
     * 
     * @param mcnum 生产工票主键
     * @return 结果
     */
    public int deleteProductionOfWorkTicketsByMcnum(Long mcnum);

    /**
     * 批量删除生产工票
     * 
     * @param mcnums 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionOfWorkTicketsByMcnums(Long[] mcnums);
}
