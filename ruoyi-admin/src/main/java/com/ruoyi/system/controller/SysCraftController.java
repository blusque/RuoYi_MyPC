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
import com.ruoyi.system.domain.SysCraft;
import com.ruoyi.system.service.ISysCraftService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工艺Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/system/craft")
public class SysCraftController extends BaseController
{
    @Autowired
    private ISysCraftService sysCraftService;

    /**
     * 查询工艺列表
     */
    @PreAuthorize("@ss.hasPermi('system:craft:list')")
    @GetMapping("/list")
    public AjaxResult list(SysCraft sysCraft)
    {
        List<SysCraft> list = sysCraftService.selectSysCraftList(sysCraft);
        return AjaxResult.success(list);
    }

    /**
     * 导出工艺列表
     */
    @PreAuthorize("@ss.hasPermi('system:craft:export')")
    @Log(title = "工艺", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCraft sysCraft)
    {
        List<SysCraft> list = sysCraftService.selectSysCraftList(sysCraft);
        ExcelUtil<SysCraft> util = new ExcelUtil<SysCraft>(SysCraft.class);
        util.exportExcel(response, list, "工艺数据");
    }

    /**
     * 获取工艺详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:craft:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCraftService.selectSysCraftById(id));
    }

    /**
     * 新增工艺
     */
    @PreAuthorize("@ss.hasPermi('system:craft:add')")
    @Log(title = "工艺", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCraft sysCraft)
    {
        return toAjax(sysCraftService.insertSysCraft(sysCraft));
    }

    /**
     * 修改工艺
     */
    @PreAuthorize("@ss.hasPermi('system:craft:edit')")
    @Log(title = "工艺", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCraft sysCraft)
    {
        return toAjax(sysCraftService.updateSysCraft(sysCraft));
    }

    /**
     * 删除工艺
     */
    @PreAuthorize("@ss.hasPermi('system:craft:remove')")
    @Log(title = "工艺", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCraftService.deleteSysCraftByIds(ids));
    }
}
