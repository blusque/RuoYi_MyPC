package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.Customer;
import com.ruoyi.market.domain.CustomerAddress;

/**
 * 客户管理Service接口
 * 
 * @author mei
 * @date 2022-10-27
 */
public interface ICustomerService {
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 按客户管理主键查询客户地址
     * 
     * @param customerId 客户管理主键
     * @return
     */
    public List<CustomerAddress> selectCustomerAddressListByCustomerId(Long customerId);

    /**
     * 按客户管理主键查询客户地址
     * 
     * @param customerAddress 客户管理主键
     * @return
     */
    public List<CustomerAddress> selectCustomerAddressList(CustomerAddress customerAddress);

    /**
     * 新增客户地址
     *
     * @param customerAddress 客户地址
     * @return 结果
     */
    public int insertCustomerAddress(List<CustomerAddress> customerAddressList);

    /**
     * 修改客户地址
     *
     * @param customerAddress 客户地址
     * @return 结果
     */
    public int updateCustomerAddress(List<CustomerAddress> customerAddressList);

    /**
     * 删除客户地址
     *
     * @param ids 需要删除的客户地址
     * @return 结果
     */
    public int deleteCustomerAddress(CustomerAddress customerAddress);

    /**
     * 根据给定数字列出客户编号列表
     * 
     * @param partId
     * @return
     */
    public List<Long> selectCustomerIdsByGivenNum(Customer customer);

    /**
     * 根据给定部分地址列出客户地址列表
     * 
     * @param partAddress
     * @return
     */
    public List<String> selectCustomerAddressByGivenStr(CustomerAddress customerAddress);
}
