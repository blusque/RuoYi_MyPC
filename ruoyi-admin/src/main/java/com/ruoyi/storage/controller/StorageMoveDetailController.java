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
import com.ruoyi.storage.domain.StorageMoveDetail;
import com.ruoyi.storage.service.IStorageMoveDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 移动明细Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storagemovedetail/movedetail")
public class StorageMoveDetailController extends BaseController
{
    @Autowired
    private IStorageMoveDetailService storageMoveDetailService;

    /**
     * 查询移动明细列表
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageMoveDetail storageMoveDetail)
    {
        startPage();
        List<StorageMoveDetail> list = storageMoveDetailService.selectStorageMoveDetailList(storageMoveDetail);
        return getDataTable(list);
    }

    /**
     * 导出移动明细列表
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:export')")
    @Log(title = "移动明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageMoveDetail storageMoveDetail)
    {
        List<StorageMoveDetail> list = storageMoveDetailService.selectStorageMoveDetailList(storageMoveDetail);
        ExcelUtil<StorageMoveDetail> util = new ExcelUtil<StorageMoveDetail>(StorageMoveDetail.class);
        util.exportExcel(response, list, "移动明细数据");
    }

    /**
     * 获取移动明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageMoveDetailService.selectStorageMoveDetailById(id));
    }

    /**
     * 新增移动明细
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:add')")
    @Log(title = "移动明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageMoveDetail storageMoveDetail)
    {
        return toAjax(storageMoveDetailService.insertStorageMoveDetail(storageMoveDetail));
    }

    /**
     * 修改移动明细
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:edit')")
    @Log(title = "移动明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageMoveDetail storageMoveDetail)
    {
        return toAjax(storageMoveDetailService.updateStorageMoveDetail(storageMoveDetail));
    }

    /**
     * 删除移动明细
     */
    @PreAuthorize("@ss.hasPermi('storagemovedetail:movedetail:remove')")
    @Log(title = "移动明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageMoveDetailService.deleteStorageMoveDetailByIds(ids));
    }
}
