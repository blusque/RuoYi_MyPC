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
import com.ruoyi.storage.domain.StorageRecord;
import com.ruoyi.storage.service.IStorageRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存信息Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storagerecord/record")
public class StorageRecordController extends BaseController
{
    @Autowired
    private IStorageRecordService storageRecordService;

    /**
     * 查询库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageRecord storageRecord)
    {
        startPage();
        List<StorageRecord> list = storageRecordService.selectStorageRecordList(storageRecord);
        return getDataTable(list);
    }

    /**
     * 导出库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:export')")
    @Log(title = "库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageRecord storageRecord)
    {
        List<StorageRecord> list = storageRecordService.selectStorageRecordList(storageRecord);
        ExcelUtil<StorageRecord> util = new ExcelUtil<StorageRecord>(StorageRecord.class);
        util.exportExcel(response, list, "库存信息数据");
    }

    /**
     * 获取库存信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageRecordService.selectStorageRecordById(id));
    }

    /**
     * 新增库存信息
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:add')")
    @Log(title = "库存信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageRecord storageRecord)
    {
        return toAjax(storageRecordService.insertStorageRecord(storageRecord));
    }

    /**
     * 修改库存信息
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:edit')")
    @Log(title = "库存信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageRecord storageRecord)
    {
        return toAjax(storageRecordService.updateStorageRecord(storageRecord));
    }

    /**
     * 删除库存信息
     */
    @PreAuthorize("@ss.hasPermi('storagerecord:record:remove')")
    @Log(title = "库存信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageRecordService.deleteStorageRecordByIds(ids));
    }
}
