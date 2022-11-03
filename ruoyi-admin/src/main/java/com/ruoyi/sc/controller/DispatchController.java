package com.ruoyi.sc.controller;

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
import com.ruoyi.sc.domain.Dispatch;
import com.ruoyi.sc.service.IDispatchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 派工单子Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/dispatch/dispatch")
public class DispatchController extends BaseController
{
    @Autowired
    private IDispatchService dispatchService;

    /**
     * 查询派工单子列表
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dispatch dispatch)
    {
        startPage();
        List<Dispatch> list = dispatchService.selectDispatchList(dispatch);
        return getDataTable(list);
    }

    /**
     * 导出派工单子列表
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:export')")
    @Log(title = "派工单子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Dispatch dispatch)
    {
        List<Dispatch> list = dispatchService.selectDispatchList(dispatch);
        ExcelUtil<Dispatch> util = new ExcelUtil<Dispatch>(Dispatch.class);
        util.exportExcel(response, list, "派工单子数据");
    }

    /**
     * 获取派工单子详细信息
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:query')")
    @GetMapping(value = "/{mcnum}")
    public AjaxResult getInfo(@PathVariable("mcnum") Long mcnum)
    {
        return AjaxResult.success(dispatchService.selectDispatchByMcnum(mcnum));
    }

    /**
     * 新增派工单子
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:add')")
    @Log(title = "派工单子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dispatch dispatch)
    {
        return toAjax(dispatchService.insertDispatch(dispatch));
    }

    /**
     * 修改派工单子
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:edit')")
    @Log(title = "派工单子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dispatch dispatch)
    {
        return toAjax(dispatchService.updateDispatch(dispatch));
    }

    /**
     * 删除派工单子
     */
    @PreAuthorize("@ss.hasPermi('dispatch:dispatch:remove')")
    @Log(title = "派工单子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mcnums}")
    public AjaxResult remove(@PathVariable Long[] mcnums)
    {
        return toAjax(dispatchService.deleteDispatchByMcnums(mcnums));
    }
}
