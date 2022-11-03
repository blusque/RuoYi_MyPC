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
import com.ruoyi.sc.domain.PickingListItems;
import com.ruoyi.sc.service.IPickingListItemsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 领料单物料Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/list/list")
public class PickingListItemsController extends BaseController
{
    @Autowired
    private IPickingListItemsService pickingListItemsService;

    /**
     * 查询领料单物料列表
     */
    @PreAuthorize("@ss.hasPermi('list:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(PickingListItems pickingListItems)
    {
        startPage();
        List<PickingListItems> list = pickingListItemsService.selectPickingListItemsList(pickingListItems);
        return getDataTable(list);
    }

    /**
     * 导出领料单物料列表
     */
    @PreAuthorize("@ss.hasPermi('list:list:export')")
    @Log(title = "领料单物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PickingListItems pickingListItems)
    {
        List<PickingListItems> list = pickingListItemsService.selectPickingListItemsList(pickingListItems);
        ExcelUtil<PickingListItems> util = new ExcelUtil<PickingListItems>(PickingListItems.class);
        util.exportExcel(response, list, "领料单物料数据");
    }

    /**
     * 获取领料单物料详细信息
     */
    @PreAuthorize("@ss.hasPermi('list:list:query')")
    @GetMapping(value = "/{mrnum}")
    public AjaxResult getInfo(@PathVariable("mrnum") Long mrnum)
    {
        return AjaxResult.success(pickingListItemsService.selectPickingListItemsByMrnum(mrnum));
    }

    /**
     * 新增领料单物料
     */
    @PreAuthorize("@ss.hasPermi('list:list:add')")
    @Log(title = "领料单物料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PickingListItems pickingListItems)
    {
        return toAjax(pickingListItemsService.insertPickingListItems(pickingListItems));
    }

    /**
     * 修改领料单物料
     */
    @PreAuthorize("@ss.hasPermi('list:list:edit')")
    @Log(title = "领料单物料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PickingListItems pickingListItems)
    {
        return toAjax(pickingListItemsService.updatePickingListItems(pickingListItems));
    }

    /**
     * 删除领料单物料
     */
    @PreAuthorize("@ss.hasPermi('list:list:remove')")
    @Log(title = "领料单物料", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mrnums}")
    public AjaxResult remove(@PathVariable Long[] mrnums)
    {
        return toAjax(pickingListItemsService.deletePickingListItemsByMrnums(mrnums));
    }
}
