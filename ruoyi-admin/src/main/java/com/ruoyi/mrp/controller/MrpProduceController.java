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
import com.ruoyi.mrp.domain.MrpProduce;
import com.ruoyi.mrp.service.IMrpProduceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/produce/produce")
public class MrpProduceController extends BaseController
{
    @Autowired
    private IMrpProduceService mrpProduceService;

    /**
     * 查询生产列表
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:list')")
    @GetMapping("/list")
    public TableDataInfo list(MrpProduce mrpProduce)
    {
        startPage();
        List<MrpProduce> list = mrpProduceService.selectMrpProduceList(mrpProduce);
        return getDataTable(list);
    }

    /**
     * 导出生产列表
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:export')")
    @Log(title = "生产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MrpProduce mrpProduce)
    {
        List<MrpProduce> list = mrpProduceService.selectMrpProduceList(mrpProduce);
        ExcelUtil<MrpProduce> util = new ExcelUtil<MrpProduce>(MrpProduce.class);
        util.exportExcel(response, list, "生产数据");
    }

    /**
     * 获取生产详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:query')")
    @GetMapping(value = "/{mrpProduceId}")
    public AjaxResult getInfo(@PathVariable("mrpProduceId") Long mrpProduceId)
    {
        return AjaxResult.success(mrpProduceService.selectMrpProduceByMrpProduceId(mrpProduceId));
    }

    /**
     * 新增生产
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:add')")
    @Log(title = "生产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MrpProduce mrpProduce)
    {
        return toAjax(mrpProduceService.insertMrpProduce(mrpProduce));
    }

    /**
     * 修改生产
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:edit')")
    @Log(title = "生产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MrpProduce mrpProduce)
    {
        return toAjax(mrpProduceService.updateMrpProduce(mrpProduce));
    }

    /**
     * 删除生产
     */
    @PreAuthorize("@ss.hasPermi('produce:produce:remove')")
    @Log(title = "生产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mrpProduceIds}")
    public AjaxResult remove(@PathVariable Long[] mrpProduceIds)
    {
        return toAjax(mrpProduceService.deleteMrpProduceByMrpProduceIds(mrpProduceIds));
    }
}
