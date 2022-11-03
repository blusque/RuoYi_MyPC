package com.ruoyi.sc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sc.mapper.ProductionOfWorkTicketsMapper;
import com.ruoyi.sc.domain.ProductionOfWorkTickets;
import com.ruoyi.sc.service.IProductionOfWorkTicketsService;

/**
 * 生产工票Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class ProductionOfWorkTicketsServiceImpl implements IProductionOfWorkTicketsService 
{
    @Autowired
    private ProductionOfWorkTicketsMapper productionOfWorkTicketsMapper;

    /**
     * 查询生产工票
     * 
     * @param mcnum 生产工票主键
     * @return 生产工票
     */
    @Override
    public ProductionOfWorkTickets selectProductionOfWorkTicketsByMcnum(Long mcnum)
    {
        return productionOfWorkTicketsMapper.selectProductionOfWorkTicketsByMcnum(mcnum);
    }

    /**
     * 查询生产工票列表
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 生产工票
     */
    @Override
    public List<ProductionOfWorkTickets> selectProductionOfWorkTicketsList(ProductionOfWorkTickets productionOfWorkTickets)
    {
        return productionOfWorkTicketsMapper.selectProductionOfWorkTicketsList(productionOfWorkTickets);
    }

    /**
     * 新增生产工票
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 结果
     */
    @Override
    public int insertProductionOfWorkTickets(ProductionOfWorkTickets productionOfWorkTickets)
    {
        return productionOfWorkTicketsMapper.insertProductionOfWorkTickets(productionOfWorkTickets);
    }

    /**
     * 修改生产工票
     * 
     * @param productionOfWorkTickets 生产工票
     * @return 结果
     */
    @Override
    public int updateProductionOfWorkTickets(ProductionOfWorkTickets productionOfWorkTickets)
    {
        return productionOfWorkTicketsMapper.updateProductionOfWorkTickets(productionOfWorkTickets);
    }

    /**
     * 批量删除生产工票
     * 
     * @param mcnums 需要删除的生产工票主键
     * @return 结果
     */
    @Override
    public int deleteProductionOfWorkTicketsByMcnums(Long[] mcnums)
    {
        return productionOfWorkTicketsMapper.deleteProductionOfWorkTicketsByMcnums(mcnums);
    }

    /**
     * 删除生产工票信息
     * 
     * @param mcnum 生产工票主键
     * @return 结果
     */
    @Override
    public int deleteProductionOfWorkTicketsByMcnum(Long mcnum)
    {
        return productionOfWorkTicketsMapper.deleteProductionOfWorkTicketsByMcnum(mcnum);
    }
}
