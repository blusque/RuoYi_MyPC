package com.ruoyi.market.controller;

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
import com.ruoyi.market.domain.Customer;
import com.ruoyi.market.domain.CustomerAddress;
import com.ruoyi.market.service.ICustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author mei
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/market/customer")
public class CustomerController extends BaseController
{
    @Autowired
    private ICustomerService customerService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 查询客户地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/address/list")
    public TableDataInfo listAddress(CustomerAddress customerAddress)
    {
        // startPage();
        List<CustomerAddress> list = customerService.selectCustomerAddressList(customerAddress);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list/q")
    public TableDataInfo listByGivenNum(Customer customer)
    {
        // startPage();
        List<Long> list = customerService.selectCustomerIdsByGivenNum(customer);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/address/list/q")
    public TableDataInfo listAddressByGivenStr(CustomerAddress customerAddress)
    {
        // startPage();
        List<String> list = customerService.selectCustomerAddressByGivenStr(customerAddress);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/list/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerService.selectCustomerById(id));
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/address/list/{customerId}")
    public AjaxResult getAddressInfo(@PathVariable("customerId") Long customerId)
    {
        return AjaxResult.success(customerService.selectCustomerAddressListByCustomerId(customerId));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Customer customer)
    {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping("/address")
    public AjaxResult addAddress(@RequestBody List<CustomerAddress> customerAddressList)
    {
        return toAjax(customerService.insertCustomerAddress(customerAddressList));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Customer customer)
    {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/address")
    public AjaxResult editAddress(@RequestBody List<CustomerAddress> customerAddressList)
    {
        return toAjax(customerService.updateCustomerAddress(customerAddressList));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/address")
    public AjaxResult removeAddress(@RequestBody CustomerAddress customerAddress)
    {
        return toAjax(customerService.deleteCustomerAddress(customerAddress));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping(value = "/{id}")
    public AjaxResult removeById(@PathVariable("id") Long id)
    {
        return toAjax(customerService.deleteCustomerById(id));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping
    public AjaxResult removeByIds(Long[] ids)
    {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }
}
