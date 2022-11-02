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
import com.ruoyi.mrp.domain.MrpPurchase;
import com.ruoyi.mrp.service.IMrpPurchaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/purchase/purchase")
public class MrpPurchaseController extends BaseController
{
    @Autowired
    private IMrpPurchaseService mrpPurchaseService;

    /**
     * 查询采购列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(MrpPurchase mrpPurchase)
    {
        startPage();
        List<MrpPurchase> list = mrpPurchaseService.selectMrpPurchaseList(mrpPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:export')")
    @Log(title = "采购", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MrpPurchase mrpPurchase)
    {
        List<MrpPurchase> list = mrpPurchaseService.selectMrpPurchaseList(mrpPurchase);
        ExcelUtil<MrpPurchase> util = new ExcelUtil<MrpPurchase>(MrpPurchase.class);
        util.exportExcel(response, list, "采购数据");
    }

    /**
     * 获取采购详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:query')")
    @GetMapping(value = "/{mrpPurchaseId}")
    public AjaxResult getInfo(@PathVariable("mrpPurchaseId") Long mrpPurchaseId)
    {
        return AjaxResult.success(mrpPurchaseService.selectMrpPurchaseByMrpPurchaseId(mrpPurchaseId));
    }

    /**
     * 新增采购
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:add')")
    @Log(title = "采购", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MrpPurchase mrpPurchase)
    {
        return toAjax(mrpPurchaseService.insertMrpPurchase(mrpPurchase));
    }

    /**
     * 修改采购
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:edit')")
    @Log(title = "采购", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MrpPurchase mrpPurchase)
    {
        return toAjax(mrpPurchaseService.updateMrpPurchase(mrpPurchase));
    }

    /**
     * 删除采购
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:remove')")
    @Log(title = "采购", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mrpPurchaseIds}")
    public AjaxResult remove(@PathVariable Long[] mrpPurchaseIds)
    {
        return toAjax(mrpPurchaseService.deleteMrpPurchaseByMrpPurchaseIds(mrpPurchaseIds));
    }
}
