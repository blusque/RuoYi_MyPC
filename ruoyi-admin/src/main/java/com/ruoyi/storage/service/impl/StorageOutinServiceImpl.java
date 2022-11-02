package com.ruoyi.storage.service.impl;

import java.util.AbstractList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.storage.domain.StorageOutinDetail;
import com.ruoyi.storage.mapper.StorageOutinMapper;
import com.ruoyi.storage.domain.StorageOutin;
import com.ruoyi.storage.service.IStorageOutinService;
import com.ruoyi.storage.mapper.StorageRecordMapper;
import com.ruoyi.storage.domain.StorageRecord;
import com.ruoyi.storage.mapper.StorageOutinDetailMapper;

/**
 * 出入操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageOutinServiceImpl implements IStorageOutinService 
{
    @Autowired
    private StorageOutinMapper storageOutinMapper;
    @Autowired
    private StorageRecordMapper storageRecordMapper;
    @Autowired
    private StorageOutinDetailMapper storageOutinDetailMapper;

    /**
     * 查询出入操作
     * 
     * @param id 出入操作主键
     * @return 出入操作
     */
    @Override
    public StorageOutin selectStorageOutinById(Long id)
    {
        return storageOutinMapper.selectStorageOutinById(id);
    }

    /**
     * 查询出入操作列表
     * 
     * @param storageOutin 出入操作
     * @return 出入操作
     */
    @Override
    public List<StorageOutin> selectStorageOutinList(StorageOutin storageOutin)
    {
        return storageOutinMapper.selectStorageOutinList(storageOutin);
    }

    /**
     * 新增出入操作
     * 
     * @param storageOutin 出入操作
     * @return 结果
     */
    int flag = 1;
    @Transactional
    @Override
    public int insertStorageOutin(StorageOutin storageOutin)
    {
        int rows = storageOutinMapper.insertStorageOutin(storageOutin);
        insertStorageOutinDetail(storageOutin);
        updatestoragerecord(storageOutin);
        if (flag == 1) {
            return rows;
        } else {
            flag = 1;
            return 0;
        }
    }

    /**
     * 修改出入操作
     * 
     * @param storageOutin 出入操作
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStorageOutin(StorageOutin storageOutin)
    {
        storageOutinMapper.deleteStorageOutinDetailByOutinId(storageOutin.getId());
        insertStorageOutinDetail(storageOutin);
        return storageOutinMapper.updateStorageOutin(storageOutin);
    }

    /**
     * 批量删除出入操作
     * 
     * @param ids 需要删除的出入操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageOutinByIds(Long[] ids)
    {
        deletestoragerecord(ids);
        if(flag == 1) {
            storageOutinMapper.deleteStorageOutinDetailByOutinIds(ids);
            return storageOutinMapper.deleteStorageOutinByIds(ids);
        }else{
            flag = 1;
            return 0;
        }
    }

    /**
     * 删除出入操作信息
     * 
     * @param id 出入操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageOutinById(Long id)
    {
        storageOutinMapper.deleteStorageOutinDetailByOutinId(id);
        return storageOutinMapper.deleteStorageOutinById(id);
    }

    /**
     * 新增出入明细信息
     * 
     * @param storageOutin 出入操作对象
     */
    public void insertStorageOutinDetail(StorageOutin storageOutin)
    {
        List<StorageOutinDetail> storageOutinDetailList = storageOutin.getStorageOutinDetailList();
        Long id = storageOutin.getId();
        if (StringUtils.isNotNull(storageOutinDetailList))
        {
            List<StorageOutinDetail> list = new ArrayList<StorageOutinDetail>();
            for (StorageOutinDetail storageOutinDetail : storageOutinDetailList)
            {
                storageOutinDetail.setOutinId(id);
                list.add(storageOutinDetail);
            }
            if (list.size() > 0)
            {
                storageOutinMapper.batchStorageOutinDetail(list);
            }
        }
    }
    public void updatestoragerecord(StorageOutin storageOutin)
    {
        List<StorageOutinDetail> storageOutinDetailList = storageOutin.getStorageOutinDetailList();
        List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
        if (StringUtils.isNotNull(storageOutinDetailList))
        {
            for (StorageOutinDetail storageOutinDetail : storageOutinDetailList)
            {
                StorageRecord storageRecord = new StorageRecord();
                storageRecord.setMaterialId(storageOutinDetail.getMaterialId());
                storageRecord.setStorageId(storageOutinDetail.getStorageId());
                storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                //不存在则新增
                if (storageRecordList.size() == 0) {
                    storageRecord.setNumber(storageOutinDetail.getNumber());
                    if(storageRecord.getNumber() < 0)
                    {
                        //提示库存不足，出库失败
                        flag = 0;
                    } else if (storageRecord.getNumber() == 0) {
                        //提示出入库数量不可为零
                        flag = 0;
                    } else {
                        storageRecordMapper.batchStorageRecord(storageRecord);
                    }
                }
                //存在则做加法
                else if (storageRecordList.size() == 1){
                    for (StorageRecord storageRecord0 : storageRecordList) {
                        long tmp = storageOutinDetail.getNumber() + storageRecord0.getNumber();
                        if(tmp > 0)
                        {
                            storageRecord.setNumber(tmp);
                            storageRecordMapper.updateStorageRecord1(storageRecord);
                        } else if (tmp == 0) {
                            storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                        }else{
                            //提示库存不足，出库失败
                            flag = 0;
                        }
                    }
                }
            }
        }
    }

    public void deletestoragerecord(Long[] ids)
    {
        for (int i=0;i< ids.length;i++)
        {
            StorageOutinDetail storageOutinDetail = new StorageOutinDetail();
            storageOutinDetail.setOutinId(ids[i]);
            List<StorageOutinDetail> storageOutinDetailList = new ArrayList<StorageOutinDetail>();
            storageOutinDetailList = storageOutinDetailMapper.selectStorageOutinDetailList(storageOutinDetail);
            if (StringUtils.isNotNull(storageOutinDetailList))
            {
                List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
                for (StorageOutinDetail storageOutinDetail0 : storageOutinDetailList)
                {
                    StorageRecord storageRecord = new StorageRecord();
                    storageRecord.setMaterialId(storageOutinDetail0.getMaterialId());
                    storageRecord.setStorageId(storageOutinDetail0.getStorageId());
                    storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                    if (storageRecordList.size() == 1){
                        for (StorageRecord storageRecord0 : storageRecordList) {
                            long tmp = storageRecord0.getNumber() - storageOutinDetail0.getNumber();
                            if(tmp > 0)
                            {
                                storageRecord.setNumber(tmp);
                                storageRecordMapper.updateStorageRecord1(storageRecord);
                            } else if (tmp == 0) {
                                storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                            }else{
                                //提示库存不足，删除出入库单失败
                                flag = 0;
                            }
                        }
                    }else if (storageRecordList.size() == 0) {
                        storageRecord.setNumber( - storageOutinDetail.getNumber());
                        if(storageRecord.getNumber() < 0)
                        {
                            //提示库存不足，删除失败
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
