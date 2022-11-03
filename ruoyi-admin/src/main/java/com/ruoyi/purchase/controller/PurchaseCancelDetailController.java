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
import com.ruoyi.purchase.domain.PurchaseCancelDetail;
import com.ruoyi.purchase.service.IPurchaseCancelDetailService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购订单退货明细Controller
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/purchase/cancel")
public class PurchaseCancelDetailController extends BaseController
{
    @Autowired
    private IPurchaseCancelDetailService purchaseCancelDetailService;

    /**
     * 查询采购订单退货明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseCancelDetail purchaseCancelDetail)
    {
        startPage();
        List<PurchaseCancelDetail> list = purchaseCancelDetailService.selectPurchaseCancelDetailList(purchaseCancelDetail);
        return getDataTable(list);
    }

    /**
     * 导出采购订单退货明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:export')")
    @Log(title = "采购订单退货明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseCancelDetail purchaseCancelDetail)
    {
        List<PurchaseCancelDetail> list = purchaseCancelDetailService.selectPurchaseCancelDetailList(purchaseCancelDetail);
        ExcelUtil<PurchaseCancelDetail> util = new ExcelUtil<PurchaseCancelDetail>(PurchaseCancelDetail.class);
        util.exportExcel(response, list, "采购订单退货明细数据");
    }

    /**
     * 获取采购订单退货明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return AjaxResult.success(purchaseCancelDetailService.selectPurchaseCancelDetailByOrderId(orderId));
    }

    /**
     * 新增采购订单退货明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:add')")
    @Log(title = "采购订单退货明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseCancelDetail purchaseCancelDetail)
    {
        return toAjax(purchaseCancelDetailService.insertPurchaseCancelDetail(purchaseCancelDetail));
    }

    /**
     * 修改采购订单退货明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:edit')")
    @Log(title = "采购订单退货明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseCancelDetail purchaseCancelDetail)
    {
        return toAjax(purchaseCancelDetailService.updatePurchaseCancelDetail(purchaseCancelDetail));
    }

    /**
     * 删除采购订单退货明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:cancel:remove')")
    @Log(title = "采购订单退货明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(purchaseCancelDetailService.deletePurchaseCancelDetailByOrderIds(orderIds));
    }
}
