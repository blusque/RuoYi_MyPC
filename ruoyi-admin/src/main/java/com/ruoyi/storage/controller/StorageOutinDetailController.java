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
import com.ruoyi.storage.domain.StorageOutinDetail;
import com.ruoyi.storage.service.IStorageOutinDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出入明细Controller
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/storageoutindetail/outindetail")
public class StorageOutinDetailController extends BaseController
{
    @Autowired
    private IStorageOutinDetailService storageOutinDetailService;

    /**
     * 查询出入明细列表
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageOutinDetail storageOutinDetail)
    {
        startPage();
        List<StorageOutinDetail> list = storageOutinDetailService.selectStorageOutinDetailList(storageOutinDetail);
        return getDataTable(list);
    }

    /**
     * 导出出入明细列表
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:export')")
    @Log(title = "出入明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageOutinDetail storageOutinDetail)
    {
        List<StorageOutinDetail> list = storageOutinDetailService.selectStorageOutinDetailList(storageOutinDetail);
        ExcelUtil<StorageOutinDetail> util = new ExcelUtil<StorageOutinDetail>(StorageOutinDetail.class);
        util.exportExcel(response, list, "出入明细数据");
    }

    /**
     * 获取出入明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageOutinDetailService.selectStorageOutinDetailById(id));
    }

    /**
     * 新增出入明细
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:add')")
    @Log(title = "出入明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageOutinDetail storageOutinDetail)
    {
        return toAjax(storageOutinDetailService.insertStorageOutinDetail(storageOutinDetail));
    }

    /**
     * 修改出入明细
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:edit')")
    @Log(title = "出入明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageOutinDetail storageOutinDetail)
    {
        return toAjax(storageOutinDetailService.updateStorageOutinDetail(storageOutinDetail));
    }

    /**
     * 删除出入明细
     */
    @PreAuthorize("@ss.hasPermi('storageoutindetail:outindetail:remove')")
    @Log(title = "出入明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageOutinDetailService.deleteStorageOutinDetailByIds(ids));
    }
}
