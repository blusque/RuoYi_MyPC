package com.ruoyi.market.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.market.domain.Customer;
import com.ruoyi.market.domain.CustomerAddress;
import com.ruoyi.market.mapper.CustomerMapper;
import com.ruoyi.market.mapper.CustomerAddressMapper;
import com.ruoyi.market.service.ICustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author mei
 * @date 2022-10-27
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerAddressMapper customerAddressMapper;

    // @Autowired
    // private List<String> totalCustomerIds;

    // @Autowired
    // private List<String> totalCustomerAddress;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public Customer selectCustomerById(Long id) {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCustomer(Customer customer) {
        int row = customerMapper.insertCustomer(customer);
        List<CustomerAddress> temp = customer.getCustomerAddressList();
        customer = customerMapper.selectCustomerList(customer).get(0);
        customer.setCustomerAddressList(temp);
        insertCustomerAddress(customer);
        return row;
    }

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCustomer(Customer customer) {
        updateCustomerAddress(customer);
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        customerAddressMapper.deleteCustomerAddressByCustomerIds(ids);
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCustomerById(Long id) {
        customerAddressMapper.deleteCustomerAddressByCustomerId(id);
        return customerMapper.deleteCustomerById(id);
    }

    /**
     * 
     * 按客户管理主键查询客户地址
     * 
     * @param customerId 客户管理主键
     * @return 客户地址列表
     */
    @Override
    public List<CustomerAddress> selectCustomerAddressListByCustomerId(Long customerId) {
        return customerAddressMapper.selectCustomerAddressListByCustomerId(customerId);
    }

    /**
     * 按客户管理主键查询客户地址
     * 
     * @param customerAddress 客户管理主键
     * @return
     */
    @Override
    public List<CustomerAddress> selectCustomerAddressList(CustomerAddress customerAddress) {
        return customerAddressMapper.selectCustomerAddressList(customerAddress);
    }

    /**
     * 新增客户地址信息
     * 
     * @param customer 客户管理对象
     */
    @Override
    public int insertCustomerAddress(List<CustomerAddress> customerAddressList) {
        return customerAddressMapper.batchCustomerAddress(customerAddressList);
    }

    /**
     * 从地址页面修改客户地址信息，前端保证传入的customerAddressList包含相同的customerId
     * 
     * @param customerAddressList
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCustomerAddress(List<CustomerAddress> customerAddressList) {
        Long id = customerAddressList.get(0).getCustomerId();
        customerAddressMapper.deleteCustomerAddressByCustomerId(id);
        return insertCustomerAddress(customerAddressList);
    }

    /**
     * 新增客户地址信息, 前端保证新增时一定存在一个地址
     * 
     * @param customer 客户管理对象
     */
    public void insertCustomerAddress(Customer customer) {
        List<CustomerAddress> customerAddressList = customer.getCustomerAddressList();
        Long id = customer.getId();
        List<CustomerAddress> list = new ArrayList<CustomerAddress>();
        for (CustomerAddress customerAddress : customerAddressList) {
            customerAddress.setCustomerId(id);
            System.out.println(customerAddress);
            list.add(customerAddress);
        }
        insertCustomerAddress(list);
    }

    /**
     * 
     * @param customer
     */
    public void updateCustomerAddress(Customer customer) {
        Long id = customer.getId();
        customerAddressMapper.deleteCustomerAddressByCustomerId(id);
        insertCustomerAddress(customer);
    }

    /**
     * 删除客户地址
     *
     * @param ids 需要删除的客户地址
     * @return 结果
     */
    @Override
    public int deleteCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressMapper.deleteCustomerAddress(customerAddress);
    }

    /**
     * 根据给定数字列出客户编号列表
     * 
     * @param partId
     * @return
     */
    @Override
    public List<Long> selectCustomerIdsByGivenNum(Customer customer) {
        Long partId = customer.getId();
        List<Long> result = new ArrayList<Long>();
        List<Customer> list = new ArrayList<Customer>();
        String idStr = null;
        System.out.println(partId);
        if (partId == null) {
            list = customerMapper.selectCustomerList(null);
        }
        else {
            idStr = partId.toString();
            list = customerMapper.selectCustomerByLargerThanId(partId);
        }

        if (idStr == null) {
            for (Customer _customer : list) {
                result.add(_customer.getId());
            }
            System.out.println(result);
        }
        else {
            for (Customer _customer : list) {
                Long id = _customer.getId();
                String str = id.toString();
                boolean has = true;
                for (int i = 0; i < idStr.length(); i++) {
                    if (idStr.charAt(i) != str.charAt(i)) {
                        has = false;
                    }
                }
                if (has) {
                    result.add(id);
                }
            }
        }
        
        return result;
    }

    /**
     * 根据给定部分地址列出客户地址列表
     * 
     * @param partAddress
     * @return
     */
    @Override
    public List<String> selectCustomerAddressByGivenStr(CustomerAddress customerAddress) {
        List<String> result = new ArrayList<String>();
        if (customerAddress.getCustomerId() == null)
            return result;
        List<CustomerAddress> list = customerAddressMapper.selectCustomerAddressListLikeAddress(customerAddress);
        for (CustomerAddress item : list) {
            result.add(item.getCustomerAddress());
        }
        return result;
    }
}
