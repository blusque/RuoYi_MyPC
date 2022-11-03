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
import com.ruoyi.purchase.domain.SupplierDetail;
import com.ruoyi.purchase.service.ISupplierDetailService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商明细Controller
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/purchase/supplier/detail")
public class SupplierDetailController extends BaseController
{
    @Autowired
    private ISupplierDetailService supplierDetailService;

    /**
     * 查询供应商明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplierDetail supplierDetail)
    {
        startPage();
        List<SupplierDetail> list = supplierDetailService.selectSupplierDetailList(supplierDetail);
        return getDataTable(list);
    }

    /**
     * 导出供应商明细列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:export')")
    @Log(title = "供应商明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplierDetail supplierDetail)
    {
        List<SupplierDetail> list = supplierDetailService.selectSupplierDetailList(supplierDetail);
        ExcelUtil<SupplierDetail> util = new ExcelUtil<SupplierDetail>(SupplierDetail.class);
        util.exportExcel(response, list, "供应商明细数据");
    }

    /**
     * 获取供应商明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return AjaxResult.success(supplierDetailService.selectSupplierDetailBySupplierId(supplierId));
    }

    /**
     * 新增供应商明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:add')")
    @Log(title = "供应商明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplierDetail supplierDetail)
    {
        return toAjax(supplierDetailService.insertSupplierDetail(supplierDetail));
    }

    /**
     * 修改供应商明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:edit')")
    @Log(title = "供应商明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplierDetail supplierDetail)
    {
        return toAjax(supplierDetailService.updateSupplierDetail(supplierDetail));
    }

    /**
     * 删除供应商明细
     */
    @PreAuthorize("@ss.hasPermi('purchase:detail:remove')")
    @Log(title = "供应商明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(supplierDetailService.deleteSupplierDetailBySupplierIds(supplierIds));
    }
}
