package com.ruoyi.market_yan.controller;

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
import com.ruoyi.market_yan.domain.MarketOrder;
import com.ruoyi.market_yan.service.IMarketOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 *
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/market_yan/order")
public class MarketOrderController extends BaseController
{
    @Autowired
    private IMarketOrderService marketOrderService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketOrder marketOrder)
    {
        startPage();
        List<MarketOrder> list = marketOrderService.selectMarketOrderList(marketOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketOrder marketOrder)
    {
        List<MarketOrder> list = marketOrderService.selectMarketOrderList(marketOrder);
        ExcelUtil<MarketOrder> util = new ExcelUtil<MarketOrder>(MarketOrder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(marketOrderService.selectMarketOrderById(id));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketOrder marketOrder)
    {
        return toAjax(marketOrderService.insertMarketOrder(marketOrder));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketOrder marketOrder)
    {
        return toAjax(marketOrderService.updateMarketOrder(marketOrder));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('market_yan:order:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketOrderService.deleteMarketOrderByIds(ids));
    }
}
