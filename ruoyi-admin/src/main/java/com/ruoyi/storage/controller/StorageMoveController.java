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
import com.ruoyi.storage.domain.StorageMove;
import com.ruoyi.storage.service.IStorageMoveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 移动操作Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storagemove/move")
public class StorageMoveController extends BaseController
{
    @Autowired
    private IStorageMoveService storageMoveService;

    /**
     * 查询移动操作列表
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageMove storageMove)
    {
        startPage();
        List<StorageMove> list = storageMoveService.selectStorageMoveList(storageMove);
        return getDataTable(list);
    }

    /**
     * 导出移动操作列表
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:export')")
    @Log(title = "移动操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageMove storageMove)
    {
        List<StorageMove> list = storageMoveService.selectStorageMoveList(storageMove);
        ExcelUtil<StorageMove> util = new ExcelUtil<StorageMove>(StorageMove.class);
        util.exportExcel(response, list, "移动操作数据");
    }

    /**
     * 获取移动操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageMoveService.selectStorageMoveById(id));
    }

    /**
     * 新增移动操作
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:add')")
    @Log(title = "移动操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageMove storageMove)
    {
        return toAjax(storageMoveService.insertStorageMove(storageMove));
    }

    /**
     * 修改移动操作
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:edit')")
    @Log(title = "移动操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageMove storageMove)
    {
        return toAjax(storageMoveService.updateStorageMove(storageMove));
    }

    /**
     * 删除移动操作
     */
    @PreAuthorize("@ss.hasPermi('storagemove:move:remove')")
    @Log(title = "移动操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageMoveService.deleteStorageMoveByIds(ids));
    }
}
