package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SysMaterial;
import com.ruoyi.system.service.ISysMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料管理Controller
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/system/material")
public class SysMaterialController extends BaseController
{
    @Autowired
    private ISysMaterialService sysMaterialService;

    /**
     * 查询物料管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMaterial sysMaterial)
    {
        startPage();
        List<SysMaterial> list = sysMaterialService.selectSysMaterialList(sysMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物料管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @Log(title = "物料管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMaterial sysMaterial)
    {
        List<SysMaterial> list = sysMaterialService.selectSysMaterialList(sysMaterial);
        ExcelUtil<SysMaterial> util = new ExcelUtil<SysMaterial>(SysMaterial.class);
        util.exportExcel(response, list, "物料管理数据");
    }

    /**
     * 获取物料管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMaterialService.selectSysMaterialById(id));
    }

    /**
     * 新增物料管理
     */
    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @Log(title = "物料管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMaterial sysMaterial)
    {
        return toAjax(sysMaterialService.insertSysMaterial(sysMaterial));
    }

    /**
     * 修改物料管理
     */
    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @Log(title = "物料管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMaterial sysMaterial)
    {
        return toAjax(sysMaterialService.updateSysMaterial(sysMaterial));
    }

    /**
     * 删除物料管理
     */
    @PreAuthorize("@ss.hasPermi('system:material:remove')")
    @Log(title = "物料管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMaterialService.deleteSysMaterialByIds(ids));
    }
}
