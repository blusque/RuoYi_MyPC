package com.ruoyi.purchase.controller;

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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.purchase.domain.PurchaseOrderDetail;
import com.ruoyi.purchase.service.IPurchaseOrderDetailService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购订单明细Controller
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/purchase/order/detail")
public class PurchaseOrderDetailController extends BaseController
{
    @Autowired
    private IPurchaseOrderDetailService purchaseOrderDetailService;

    /**
     * 查询采购订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderDetail purchaseOrderDetail)
    {
        startPage();
        List<PurchaseOrderDetail> list = purchaseOrderDetailService.selectPurchaseOrderDetailList(purchaseOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出采购订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:export')")
    @Log(title = "采购订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseOrderDetail purchaseOrderDetail)
    {
        List<PurchaseOrderDetail> list = purchaseOrderDetailService.selectPurchaseOrderDetailList(purchaseOrderDetail);
        ExcelUtil<PurchaseOrderDetail> util = new ExcelUtil<PurchaseOrderDetail>(PurchaseOrderDetail.class);
        util.exportExcel(response, list, "采购订单明细数据");
    }

    /**
     * 获取采购订单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return AjaxResult.success(purchaseOrderDetailService.selectPurchaseOrderDetailByOrderId(orderId));
    }

    /**
     * 新增采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:add')")
    @Log(title = "采购订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrderDetail purchaseOrderDetail)
    {
        return toAjax(purchaseOrderDetailService.insertPurchaseOrderDetail(purchaseOrderDetail));
    }

    /**
     * 修改采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:edit')")
    @Log(title = "采购订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrderDetail purchaseOrderDetail)
    {
        return toAjax(purchaseOrderDetailService.updatePurchaseOrderDetail(purchaseOrderDetail));
    }

    /**
     * 删除采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:remove')")
    @Log(title = "采购订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(purchaseOrderDetailService.deletePurchaseOrderDetailByOrderIds(orderIds));
    }
}
