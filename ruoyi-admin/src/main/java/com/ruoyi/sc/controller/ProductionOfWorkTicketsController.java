package com.ruoyi.sc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.sc.domain.ProductionOfWorkTickets;
import com.ruoyi.sc.service.IProductionOfWorkTicketsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产工票Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/tickets/tickets")
public class ProductionOfWorkTicketsController extends BaseController
{
    @Autowired
    private IProductionOfWorkTicketsService productionOfWorkTicketsService;

    /**
     * 查询生产工票列表
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionOfWorkTickets productionOfWorkTickets)
    {
        startPage();
        List<ProductionOfWorkTickets> list = productionOfWorkTicketsService.selectProductionOfWorkTicketsList(productionOfWorkTickets);
        return getDataTable(list);
    }

    /**
     * 导出生产工票列表
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:export')")
    @Log(title = "生产工票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionOfWorkTickets productionOfWorkTickets)
    {
        List<ProductionOfWorkTickets> list = productionOfWorkTicketsService.selectProductionOfWorkTicketsList(productionOfWorkTickets);
        ExcelUtil<ProductionOfWorkTickets> util = new ExcelUtil<ProductionOfWorkTickets>(ProductionOfWorkTickets.class);
        util.exportExcel(response, list, "生产工票数据");
    }

    /**
     * 获取生产工票详细信息
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:query')")
    @GetMapping(value = "/{mcnum}")
    public AjaxResult getInfo(@PathVariable("mcnum") Long mcnum)
    {
        return AjaxResult.success(productionOfWorkTicketsService.selectProductionOfWorkTicketsByMcnum(mcnum));
    }

    /**
     * 新增生产工票
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:add')")
    @Log(title = "生产工票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionOfWorkTickets productionOfWorkTickets)
    {
        return toAjax(productionOfWorkTicketsService.insertProductionOfWorkTickets(productionOfWorkTickets));
    }

    /**
     * 修改生产工票
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:edit')")
    @Log(title = "生产工票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionOfWorkTickets productionOfWorkTickets)
    {
        return toAjax(productionOfWorkTicketsService.updateProductionOfWorkTickets(productionOfWorkTickets));
    }

    /**
     * 删除生产工票
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:remove')")
    @Log(title = "生产工票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mcnums}")
    public AjaxResult remove(@PathVariable Long[] mcnums)
    {
        return toAjax(productionOfWorkTicketsService.deleteProductionOfWorkTicketsByMcnums(mcnums));
    }
}
