package com.ruoyi.storage.service.impl;

import java.util.List;

import com.ruoyi.storage.domain.*;
import com.ruoyi.storage.mapper.StorageOutinDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.storage.mapper.StorageCheckMapper;
import com.ruoyi.storage.service.IStorageCheckService;
import com.ruoyi.storage.mapper.StorageRecordMapper;
import com.ruoyi.storage.mapper.StorageCheckDetailMapper;

/**
 * 盘点操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageCheckServiceImpl implements IStorageCheckService 
{
    @Autowired
    private StorageCheckMapper storageCheckMapper;
    @Autowired
    private StorageRecordMapper storageRecordMapper;
    @Autowired
    private StorageCheckDetailMapper StorageCheckDetailMapper;

    /**
     * 查询盘点操作
     * 
     * @param id 盘点操作主键
     * @return 盘点操作
     */
    @Override
    public StorageCheck selectStorageCheckById(Long id)
    {
        return storageCheckMapper.selectStorageCheckById(id);
    }

    /**
     * 查询盘点操作列表
     * 
     * @param storageCheck 盘点操作
     * @return 盘点操作
     */
    @Override
    public List<StorageCheck> selectStorageCheckList(StorageCheck storageCheck)
    {
        return storageCheckMapper.selectStorageCheckList(storageCheck);
    }

    /**
     * 新增盘点操作
     * 
     * @param storageCheck 盘点操作
     * @return 结果
     */
    int flag = 1;
    @Transactional
    @Override
    public int insertStorageCheck(StorageCheck storageCheck)
    {
        if (flag == 1) {
            int rows = storageCheckMapper.insertStorageCheck(storageCheck);
            insertStorageCheckDetail(storageCheck);
            updatestoragerecord(storageCheck);
            return rows;
        }else{
            flag = 1;
            return 0;
        }
    }

    /**
     * 修改盘点操作
     * 
     * @param storageCheck 盘点操作
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStorageCheck(StorageCheck storageCheck)
    {
        storageCheckMapper.deleteStorageCheckDetailByCheckId(storageCheck.getId());
        insertStorageCheckDetail(storageCheck);
        return storageCheckMapper.updateStorageCheck(storageCheck);
    }

    /**
     * 批量删除盘点操作
     * 
     * @param ids 需要删除的盘点操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageCheckByIds(Long[] ids)
    {
        deletestoragerecord(ids);
        if(flag == 1) {
            storageCheckMapper.deleteStorageCheckDetailByCheckIds(ids);
            return storageCheckMapper.deleteStorageCheckByIds(ids);
        }else{
            flag = 1;
            return 0;
        }
    }

    /**
     * 删除盘点操作信息
     * 
     * @param id 盘点操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageCheckById(Long id)
    {
        storageCheckMapper.deleteStorageCheckDetailByCheckId(id);
        return storageCheckMapper.deleteStorageCheckById(id);
    }

    /**
     * 新增盘点明细信息
     * 
     * @param storageCheck 盘点操作对象
     */
    public void insertStorageCheckDetail(StorageCheck storageCheck)
    {
        List<StorageCheckDetail> storageCheckDetailList = storageCheck.getStorageCheckDetailList();
        Long id = storageCheck.getId();
        if (StringUtils.isNotNull(storageCheckDetailList))
        {
            List<StorageCheckDetail> list = new ArrayList<StorageCheckDetail>();
            for (StorageCheckDetail storageCheckDetail : storageCheckDetailList)
            {
                storageCheckDetail.setCheckId(id);
                list.add(storageCheckDetail);
            }
            if (list.size() > 0)
            {
                storageCheckMapper.batchStorageCheckDetail(list);
            }
        }
    }

    public void updatestoragerecord(StorageCheck storageCheck)
    {
        List<StorageCheckDetail> storageCheckDetailList = storageCheck.getStorageCheckDetailList();
        List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
        if (StringUtils.isNotNull(storageCheckDetailList))
        {
            for (StorageCheckDetail storageCheckDetail : storageCheckDetailList)
            {
                StorageRecord storageRecord = new StorageRecord();
                storageRecord.setMaterialId(storageCheckDetail.getMaterialId());
                storageRecord.setStorageId(storageCheckDetail.getStorageId());
                storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                if (storageRecordList.size() == 1){
                    for (StorageRecord storageRecord0 : storageRecordList) {
                        long tmp = storageCheckDetail.getNumber() + storageRecord0.getNumber();
                        if(tmp > 0)
                        {
                            storageRecord.setNumber(tmp);
                            storageRecordMapper.updateStorageRecord1(storageRecord);
                        } else if (tmp == 0) {
                            storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                        }else{
                            //提示库存不足，请检查盘点库位的物料存储数量
                            flag = 0;
                        }
                    }
                } else if (storageRecordList.size() == 0){
                    storageRecord.setNumber(storageCheckDetail.getNumber());
                    if(storageRecord.getNumber() < 0)
                    {
                        //提示库存不足，请检查盘点库位的物料存储数量
                        flag = 0;
                    } else if (storageRecord.getNumber() > 0) {
                        storageRecordMapper.batchStorageRecord(storageRecord);
                    }
                }
            }
        }
    }

    public void deletestoragerecord(Long[] ids)
    {
        for (int i=0;i< ids.length;i++)
        {
            StorageCheckDetail storageCheckDetail = new StorageCheckDetail();
            storageCheckDetail.setCheckId(ids[i]);
            List<StorageCheckDetail> storageCheckDetailList = new ArrayList<StorageCheckDetail>();
            storageCheckDetailList = StorageCheckDetailMapper.selectStorageCheckDetailList(storageCheckDetail);
            if (StringUtils.isNotNull(storageCheckDetailList))
            {
                List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
                for (StorageCheckDetail storageCheckDetail0 : storageCheckDetailList)
                {
                    StorageRecord storageRecord = new StorageRecord();
                    storageRecord.setMaterialId(storageCheckDetail0.getMaterialId());
                    storageRecord.setStorageId(storageCheckDetail0.getStorageId());
                    storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                    if (storageRecordList.size() == 1){
                        for (StorageRecord storageRecord0 : storageRecordList) {
                            long tmp = storageRecord0.getNumber() - storageCheckDetail0.getNumber();
                            if(tmp > 0)
                            {
                                storageRecord.setNumber(tmp);
                                storageRecordMapper.updateStorageRecord1(storageRecord);
                            } else if (tmp == 0) {
                                storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                            }else{
                                //提示库存不足，删除盘点单失败
                                flag = 0;
                            }
                        }
                    }else if(storageRecordList.size() == 0){
                        storageRecord.setNumber( - storageCheckDetail0.getNumber());
                        if(storageRecord.getNumber() < 0)
                        {
                            //提示库存不足，请检查盘点库位的物料存储数量
                            flag = 0;
                        } else if (storageRecord.getNumber() > 0) {
                            storageRecordMapper.batchStorageRecord(storageRecord);
                        }
                    }
                }
            }
        }
    }
}
