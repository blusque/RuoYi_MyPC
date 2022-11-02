package com.ruoyi.market.controller;

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
import com.ruoyi.market.domain.MarketOrder;
import com.ruoyi.market.domain.MarketOrderDetail;
import com.ruoyi.market.domain.MarketCancelDetail;
import com.ruoyi.market.domain.FromTo;
import com.ruoyi.market.service.IMarketOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author mei
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/market/order")
public class MarketOrderController extends BaseController
{
    @Autowired
    private IMarketOrderService marketOrderService;

    /**
     * 查询销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:list')")
    @PutMapping("/list")
    public AjaxResult list(@RequestBody MarketOrder marketOrder)
    {
        startPage();
        return AjaxResult.success(marketOrderService.selectMarketOrderList(marketOrder));
    }

    /**
     * 查询销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:list')")
    @GetMapping("/list/from_to")
    public TableDataInfo list(FromTo fromTo)
    {
        List<MarketOrder> list = marketOrderService.selectMarketOrderListFromTo(fromTo);
        return getDataTable(list);
    }

    /**
     * 查询订单明细
     * 
     * @param marketOrderDetail
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:list')")
    @GetMapping("/list/detail")
    public TableDataInfo listDetail(MarketOrderDetail marketOrderDetail)
    {
        List<MarketOrderDetail> list = marketOrderService.selectMarketOrderDetailList(marketOrderDetail);
        return getDataTable(list); 
    }

    /**
     * 查询退货明细
     * 
     * @param marketCancelDetail
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:list')")
    @GetMapping("/list/cancel")
    public TableDataInfo listCancel(MarketCancelDetail marketCancelDetail)
    {
        List<MarketCancelDetail> list = marketOrderService.selectMarketCancelDetailList(marketCancelDetail);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:export')")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketOrder marketOrder)
    {
        List<MarketOrder> list = marketOrderService.selectMarketOrderList(marketOrder);
        ExcelUtil<MarketOrder> util = new ExcelUtil<MarketOrder>(MarketOrder.class);
        util.exportExcel(response, list, "销售订单数据");
    }

    // /**
    //  * 获取销售订单详细信息
    //  */
    // @PreAuthorize("@ss.hasPermi('system:market_order:query')")
    // @GetMapping(value = "/{id}")
    // public AjaxResult get(@PathVariable("id") Long id)
    // {
    //     return AjaxResult.success(marketOrderService.selectMarketOrderById(id));
    // }

    /**
     * 获取销售订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:query')")
    @GetMapping(value = "/{ids}")
    public AjaxResult getList(@PathVariable("ids") Long[] ids)
    {
        return AjaxResult.success(marketOrderService.selectMarketOrderListByIds(ids));
    }

    /**
     * 新增销售订单
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:add')")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketOrder marketOrder)
    {
        return toAjax(marketOrderService.insertMarketOrder(marketOrder));
    }

    @PreAuthorize("@ss.hasPermi('system:market_order:add')")
    @Log(title = "销售订单明细", businessType = BusinessType.INSERT)
    @PostMapping("/detail")
    public AjaxResult addDetail(@RequestBody List<MarketOrderDetail> marketOrderDetailList)
    {
        return toAjax(marketOrderService.insertMarketOrderDetail(marketOrderDetailList));
    }

    @PreAuthorize("@ss.hasPermi('system:market_order:add')")
    @Log(title = "销售退货明细", businessType = BusinessType.INSERT)
    @PostMapping("/cancel")
    public AjaxResult addCancel(@RequestBody List<MarketCancelDetail> marketCancelDetailList)
    {
        return toAjax(marketOrderService.insertMarketCancelDetail(marketCancelDetailList));
    }

    /**
     * 修改销售订单
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:edit')")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketOrder marketOrder)
    {
        return toAjax(marketOrderService.updateMarketOrder(marketOrder));
    }

    /**
     * 修改销售订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:edit')")
    @Log(title = "销售订单明细", businessType = BusinessType.UPDATE)
    @PutMapping("/detail")
    public AjaxResult editDetail(@RequestBody List<MarketOrderDetail> marketOrderDetailList)
    {
        return toAjax(marketOrderService.updateMarketOrderDetail(marketOrderDetailList));
    }

    /**
     * 修改销售退货明细
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:edit')")
    @Log(title = "销售订单明细", businessType = BusinessType.UPDATE)
    @PutMapping("/cancel")
    public AjaxResult editCancel(@RequestBody List<MarketCancelDetail> marketCancelDetailList)
    {
        return toAjax(marketOrderService.updateMarketCancelDetail(marketCancelDetailList));
    }

    /**
     * 删除销售订单
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:remove')")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketOrderService.deleteMarketOrderByIds(ids));
    }

    /**
     * 删除销售订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:remove')")
    @Log(title = "销售订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/detail/{ids}")
    public AjaxResult removeDetail(@PathVariable Long[] ids)
    {
        return toAjax(marketOrderService.deleteMarketOrderDetailByIds(ids));
    }

    /**
     * 删除销售退货明细
     */
    @PreAuthorize("@ss.hasPermi('system:market_order:remove')")
    @Log(title = "销售退货明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/cancel/{ids}")
    public AjaxResult removeCancel(@PathVariable Long[] ids)
    {
        return toAjax(marketOrderService.deleteMarketCancelDetailByIds(ids));
    }
}
