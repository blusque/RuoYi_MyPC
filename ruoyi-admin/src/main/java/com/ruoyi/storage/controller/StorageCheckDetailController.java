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
import com.ruoyi.storage.domain.StorageCheckDetail;
import com.ruoyi.storage.service.IStorageCheckDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 盘点明细Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storagecheckdetail/checkdetail")
public class StorageCheckDetailController extends BaseController
{
    @Autowired
    private IStorageCheckDetailService storageCheckDetailService;

    /**
     * 查询盘点明细列表
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageCheckDetail storageCheckDetail)
    {
        startPage();
        List<StorageCheckDetail> list = storageCheckDetailService.selectStorageCheckDetailList(storageCheckDetail);
        return getDataTable(list);
    }

    /**
     * 导出盘点明细列表
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:export')")
    @Log(title = "盘点明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageCheckDetail storageCheckDetail)
    {
        List<StorageCheckDetail> list = storageCheckDetailService.selectStorageCheckDetailList(storageCheckDetail);
        ExcelUtil<StorageCheckDetail> util = new ExcelUtil<StorageCheckDetail>(StorageCheckDetail.class);
        util.exportExcel(response, list, "盘点明细数据");
    }

    /**
     * 获取盘点明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageCheckDetailService.selectStorageCheckDetailById(id));
    }

    /**
     * 新增盘点明细
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:add')")
    @Log(title = "盘点明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageCheckDetail storageCheckDetail)
    {
        return toAjax(storageCheckDetailService.insertStorageCheckDetail(storageCheckDetail));
    }

    /**
     * 修改盘点明细
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:edit')")
    @Log(title = "盘点明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageCheckDetail storageCheckDetail)
    {
        return toAjax(storageCheckDetailService.updateStorageCheckDetail(storageCheckDetail));
    }

    /**
     * 删除盘点明细
     */
    @PreAuthorize("@ss.hasPermi('storagecheckdetail:checkdetail:remove')")
    @Log(title = "盘点明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageCheckDetailService.deleteStorageCheckDetailByIds(ids));
    }
}
