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
import com.ruoyi.storage.domain.StorageOutin;
import com.ruoyi.storage.service.IStorageOutinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出入操作Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storageoutin/outin")
public class StorageOutinController extends BaseController
{
    @Autowired
    private IStorageOutinService storageOutinService;

    /**
     * 查询出入操作列表
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageOutin storageOutin)
    {
        startPage();
        List<StorageOutin> list = storageOutinService.selectStorageOutinList(storageOutin);
        return getDataTable(list);
    }

    /**
     * 导出出入操作列表
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:export')")
    @Log(title = "出入操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageOutin storageOutin)
    {
        List<StorageOutin> list = storageOutinService.selectStorageOutinList(storageOutin);
        ExcelUtil<StorageOutin> util = new ExcelUtil<StorageOutin>(StorageOutin.class);
        util.exportExcel(response, list, "出入操作数据");
    }

    /**
     * 获取出入操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageOutinService.selectStorageOutinById(id));
    }

    /**
     * 新增出入操作
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:add')")
    @Log(title = "出入操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageOutin storageOutin)
    {
        return toAjax(storageOutinService.insertStorageOutin(storageOutin));
    }

    /**
     * 修改出入操作
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:edit')")
    @Log(title = "出入操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageOutin storageOutin)
    {
        return toAjax(storageOutinService.updateStorageOutin(storageOutin));
    }

    /**
     * 删除出入操作
     */
    @PreAuthorize("@ss.hasPermi('storageoutin:outin:remove')")
    @Log(title = "出入操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageOutinService.deleteStorageOutinByIds(ids));
    }
}
