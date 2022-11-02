package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.CustomerAddress;

/**
 * 客户地址Mapper接口
 * 
 * @author mei
 * @date 2022-10-28
 */
public interface CustomerAddressMapper {
    /**
     * 查询客户地址
     * 
     * @param customerId 客户管理主键
     * @return 客户地址
     */
    public List<CustomerAddress> selectCustomerAddressListByCustomerId(Long customerId);

    /**
     * 查询客户地址列表
     * 
     * @param customerAddress 客户地址
     * @return 客户地址集合
     */
    public List<CustomerAddress> selectCustomerAddressList(CustomerAddress customerAddress);

    /**
     * 查询客户地址列表
     * 
     * @param customerAddress 客户地址
     * @return 客户地址集合
     */
    public List<CustomerAddress> selectCustomerAddressListLikeAddress(CustomerAddress customerAddress);

    /**
     * 新增客户地址
     * 
     * @param customerAddress 客户地址
     * @return 结果
     */
    public int batchCustomerAddress(List<CustomerAddress> customerAddress);

    /**
     * 通过主键删除客户地址
     * 
     * @param customerAddress
     * @return
     */
    public int deleteCustomerAddress(CustomerAddress customerAddress);

    /**
     * 删除客户地址
     * 
     * @param customerId 客户地址主键
     * @return 结果
     */
    public int deleteCustomerAddressByCustomerId(Long customerId);

    /**
     * 批量删除客户地址
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerAddressByCustomerIds(Long[] customerIds);
}
