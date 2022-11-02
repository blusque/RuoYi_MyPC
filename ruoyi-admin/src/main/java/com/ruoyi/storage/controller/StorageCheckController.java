package com.ruoyi.storage.controller;

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
import com.ruoyi.storage.domain.StorageCheck;
import com.ruoyi.storage.service.IStorageCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 盘点操作Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storagecheck/check")
public class StorageCheckController extends BaseController
{
    @Autowired
    private IStorageCheckService storageCheckService;

    /**
     * 查询盘点操作列表
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageCheck storageCheck)
    {
        startPage();
        List<StorageCheck> list = storageCheckService.selectStorageCheckList(storageCheck);
        return getDataTable(list);
    }

    /**
     * 导出盘点操作列表
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:export')")
    @Log(title = "盘点操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageCheck storageCheck)
    {
        List<StorageCheck> list = storageCheckService.selectStorageCheckList(storageCheck);
        ExcelUtil<StorageCheck> util = new ExcelUtil<StorageCheck>(StorageCheck.class);
        util.exportExcel(response, list, "盘点操作数据");
    }

    /**
     * 获取盘点操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageCheckService.selectStorageCheckById(id));
    }

    /**
     * 新增盘点操作
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:add')")
    @Log(title = "盘点操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageCheck storageCheck)
    {
        return toAjax(storageCheckService.insertStorageCheck(storageCheck));
    }

    /**
     * 修改盘点操作
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:edit')")
    @Log(title = "盘点操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageCheck storageCheck)
    {
        return toAjax(storageCheckService.updateStorageCheck(storageCheck));
    }

    /**
     * 删除盘点操作
     */
    @PreAuthorize("@ss.hasPermi('storagecheck:check:remove')")
    @Log(title = "盘点操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageCheckService.deleteStorageCheckByIds(ids));
    }
}
