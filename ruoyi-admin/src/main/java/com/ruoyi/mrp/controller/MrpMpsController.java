package com.ruoyi.mrp.controller;

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
import com.ruoyi.mrp.domain.MrpMps;
import com.ruoyi.mrp.service.IMrpMpsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * MPSController
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/mrp/mps")
public class MrpMpsController extends BaseController
{
    @Autowired
    private IMrpMpsService mrpMpsService;

    /**
     * 查询MPS列表
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:list')")
    @GetMapping("/list")
    public TableDataInfo list(MrpMps mrpMps)
    {
        startPage();
        List<MrpMps> list = mrpMpsService.selectMrpMpsList(mrpMps);
        return getDataTable(list);
    }

    /**
     * 导出MPS列表
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:export')")
    @Log(title = "MPS", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MrpMps mrpMps)
    {
        List<MrpMps> list = mrpMpsService.selectMrpMpsList(mrpMps);
        ExcelUtil<MrpMps> util = new ExcelUtil<MrpMps>(MrpMps.class);
        util.exportExcel(response, list, "MPS数据");
    }

    /**
     * 获取MPS详细信息
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return AjaxResult.success(mrpMpsService.selectMrpMpsByProductId(productId));
    }

    /**
     * 新增MPS
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:add')")
    @Log(title = "MPS", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MrpMps mrpMps)
    {
        return toAjax(mrpMpsService.insertMrpMps(mrpMps));
    }

    /**
     * 修改MPS
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:edit')")
    @Log(title = "MPS", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MrpMps mrpMps)
    {
        return toAjax(mrpMpsService.updateMrpMps(mrpMps));
    }

    /**
     * 删除MPS
     */
    @PreAuthorize("@ss.hasPermi('mrp:mps:remove')")
    @Log(title = "MPS", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(mrpMpsService.deleteMrpMpsByProductIds(productIds));
    }
}
