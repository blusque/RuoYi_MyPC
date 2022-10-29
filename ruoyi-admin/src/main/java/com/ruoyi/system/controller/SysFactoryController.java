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
import com.ruoyi.system.domain.SysFactory;
import com.ruoyi.system.service.ISysFactoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车间Controller
 * 
 * @author laiyuan
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/system/factory")
public class SysFactoryController extends BaseController
{
    @Autowired
    private ISysFactoryService sysFactoryService;

    /**
     * 查询车间列表
     */
    @PreAuthorize("@ss.hasPermi('system:factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFactory sysFactory)
    {
        startPage();
        List<SysFactory> list = sysFactoryService.selectSysFactoryList(sysFactory);
        return getDataTable(list);
    }

    /**
     * 导出车间列表
     */
    @PreAuthorize("@ss.hasPermi('system:factory:export')")
    @Log(title = "车间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFactory sysFactory)
    {
        List<SysFactory> list = sysFactoryService.selectSysFactoryList(sysFactory);
        ExcelUtil<SysFactory> util = new ExcelUtil<SysFactory>(SysFactory.class);
        util.exportExcel(response, list, "车间数据");
    }

    /**
     * 获取车间详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:factory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysFactoryService.selectSysFactoryById(id));
    }

    /**
     * 新增车间
     */
    @PreAuthorize("@ss.hasPermi('system:factory:add')")
    @Log(title = "车间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFactory sysFactory)
    {
        return toAjax(sysFactoryService.insertSysFactory(sysFactory));
    }

    /**
     * 修改车间
     */
    @PreAuthorize("@ss.hasPermi('system:factory:edit')")
    @Log(title = "车间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFactory sysFactory)
    {
        return toAjax(sysFactoryService.updateSysFactory(sysFactory));
    }

    /**
     * 删除车间
     */
    @PreAuthorize("@ss.hasPermi('system:factory:remove')")
    @Log(title = "车间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysFactoryService.deleteSysFactoryByIds(ids));
    }
}
