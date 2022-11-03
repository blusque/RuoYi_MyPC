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
import com.ruoyi.purchase.domain.Supplier;
import com.ruoyi.purchase.service.ISupplierService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商Controller
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/purchase/supplier")
public class SupplierController extends BaseController
{
    @Autowired
    private ISupplierService supplierService;

    /**
     * 查询供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(Supplier supplier)
    {
        startPage();
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:export')")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Supplier supplier)
    {
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        ExcelUtil<Supplier> util = new ExcelUtil<Supplier>(Supplier.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(supplierService.selectSupplierById(id));
    }

    /**
     * 新增供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:add')")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.insertSupplier(supplier));
    }

    /**
     * 修改供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:edit')")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.updateSupplier(supplier));
    }

    /**
     * 删除供应商
     */
    @PreAuthorize("@ss.hasPermi('purchase:supplier:remove')")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(supplierService.deleteSupplierByIds(ids));
    }
}
