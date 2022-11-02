package com.ruoyi.storage.service.impl;

import java.util.List;

import com.ruoyi.storage.domain.*;
import com.ruoyi.storage.mapper.StorageOutinDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.storage.mapper.StorageMoveMapper;
import com.ruoyi.storage.service.IStorageMoveService;
import com.ruoyi.storage.mapper.StorageRecordMapper;
import com.ruoyi.storage.mapper.StorageMoveDetailMapper;

/**
 * 移动操作Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-31
 */
@Service
public class StorageMoveServiceImpl implements IStorageMoveService 
{
    @Autowired
    private StorageMoveMapper storageMoveMapper;
    @Autowired
    private StorageRecordMapper storageRecordMapper;
    @Autowired
    private StorageMoveDetailMapper StorageMoveDetailMapper;

    /**
     * 查询移动操作
     * 
     * @param id 移动操作主键
     * @return 移动操作
     */
    @Override
    public StorageMove selectStorageMoveById(Long id)
    {
        return storageMoveMapper.selectStorageMoveById(id);
    }

    /**
     * 查询移动操作列表
     * 
     * @param storageMove 移动操作
     * @return 移动操作
     */
    @Override
    public List<StorageMove> selectStorageMoveList(StorageMove storageMove)
    {
        return storageMoveMapper.selectStorageMoveList(storageMove);
    }

    /**
     * 新增移动操作
     * 
     * @param storageMove 移动操作
     * @return 结果
     */
    int flag = 1;
    @Transactional
    @Override
    public int insertStorageMove(StorageMove storageMove)
    {
        if (flag == 1) {
            int rows = storageMoveMapper.insertStorageMove(storageMove);
            insertStorageMoveDetail(storageMove);
            updatestoragerecord(storageMove);
            return rows;
        }else{
            flag = 1;
            return 0;
        }
    }

    /**
     * 修改移动操作
     * 
     * @param storageMove 移动操作
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStorageMove(StorageMove storageMove)
    {
        storageMoveMapper.deleteStorageMoveDetailByMoveId(storageMove.getId());
        insertStorageMoveDetail(storageMove);
        return storageMoveMapper.updateStorageMove(storageMove);
    }

    /**
     * 批量删除移动操作
     * 
     * @param ids 需要删除的移动操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageMoveByIds(Long[] ids)
    {
        deletestoragerecord(ids);
        if(flag == 1) {
            storageMoveMapper.deleteStorageMoveDetailByMoveIds(ids);
            return storageMoveMapper.deleteStorageMoveByIds(ids);
        }else{
            flag = 1;
            return 0;
        }
    }

    /**
     * 删除移动操作信息
     * 
     * @param id 移动操作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStorageMoveById(Long id)
    {
        storageMoveMapper.deleteStorageMoveDetailByMoveId(id);
        return storageMoveMapper.deleteStorageMoveById(id);
    }

    /**
     * 新增移动明细信息
     * 
     * @param storageMove 移动操作对象
     */
    public void insertStorageMoveDetail(StorageMove storageMove)
    {
        List<StorageMoveDetail> storageMoveDetailList = storageMove.getStorageMoveDetailList();
        Long id = storageMove.getId();
        if (StringUtils.isNotNull(storageMoveDetailList))
        {
            List<StorageMoveDetail> list = new ArrayList<StorageMoveDetail>();
            for (StorageMoveDetail storageMoveDetail : storageMoveDetailList)
            {
                storageMoveDetail.setMoveId(id);
                list.add(storageMoveDetail);
            }
            if (list.size() > 0)
            {
                storageMoveMapper.batchStorageMoveDetail(list);
            }
        }
    }

    public void updatestoragerecord(StorageMove storageMove)
    {
        List<StorageMoveDetail> storageMoveDetailList = storageMove.getStorageMoveDetailList();
        List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
        if (StringUtils.isNotNull(storageMoveDetailList))
        {
            for (StorageMoveDetail storageMoveDetail : storageMoveDetailList)
            {
                StorageRecord storageRecord = new StorageRecord();
                storageRecord.setMaterialId(storageMoveDetail.getMaterialId());
                storageRecord.setStorageId(storageMoveDetail.getFrom());
                storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                //原始库位
                if (storageRecordList.size() == 1){
                    for (StorageRecord storageRecord0 : storageRecordList) {
                        if(storageMoveDetail.getNumber() <=0)
                        {
                            //提示移动数量必须大于零
                            flag = 0;
                        } else{
                            long tmp = storageRecord0.getNumber() - storageMoveDetail.getNumber();
                            if(tmp > 0)
                            {
                                storageRecord.setNumber(tmp);
                                storageRecordMapper.updateStorageRecord1(storageRecord);
                            } else if (tmp == 0) {
                                storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                            }else{
                                //提示库存不足，请检查原始库位的物料存储数量
                                flag = 0;
                            }
                        }
                    }
                }
                else if(storageRecordList.size() == 0){
                    //提示库存不足，请检查原始库位的物料存储数量
                    flag = 0;
                }
                storageRecord.setMaterialId(storageMoveDetail.getMaterialId());
                storageRecord.setStorageId(storageMoveDetail.getTo());
                storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                //目标库位
                //不存在则新增
                if (storageRecordList.size() == 0) {
                    storageRecord.setNumber(storageMoveDetail.getNumber());
                    if (storageRecord.getNumber() <= 0) {
                        //提示移动数量必须大于零
                        flag = 0;
                    } else {
                        storageRecordMapper.batchStorageRecord(storageRecord);
                    }
                }
                //存在则做加法
                else if (storageRecordList.size() == 1){
                    for (StorageRecord storageRecord0 : storageRecordList) {
                        long tmp = storageMoveDetail.getNumber() + storageRecord0.getNumber();
                        storageRecord.setNumber(tmp);
                        storageRecordMapper.updateStorageRecord1(storageRecord);
                    }
                }
            }
        }
    }

    public void deletestoragerecord(Long[] ids)
    {
        for (int i=0;i< ids.length;i++)
        {
            StorageMoveDetail storageMoveDetail = new StorageMoveDetail();
            storageMoveDetail.setMoveId(ids[i]);
            List<StorageMoveDetail> storageMoveDetailList = new ArrayList<StorageMoveDetail>();
            storageMoveDetailList = StorageMoveDetailMapper.selectStorageMoveDetailList(storageMoveDetail);
            if (StringUtils.isNotNull(storageMoveDetailList))
            {
                List<StorageRecord> storageRecordList = new ArrayList<StorageRecord>();
                for (StorageMoveDetail storageMoveDetail0 : storageMoveDetailList)
                {
                    StorageRecord storageRecord = new StorageRecord();
                    storageRecord.setMaterialId(storageMoveDetail0.getMaterialId());
                    storageRecord.setStorageId(storageMoveDetail0.getTo());
                    storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                    //目标库位
                    if (storageRecordList.size() == 1){
                        for (StorageRecord storageRecord0 : storageRecordList) {
                            long tmp = storageRecord0.getNumber() - storageMoveDetail0.getNumber();
                            if(tmp > 0)
                            {
                                storageRecord.setNumber(tmp);
                                storageRecordMapper.updateStorageRecord1(storageRecord);
                            } else if (tmp == 0) {
                                storageRecordMapper.deleteStorageRecordBynum0(storageRecord);
                            }
                        }
                    }else if(storageRecordList.size() == 0){
                        //提示库存不足，请检查目标库位的物料存储数量
                        flag = 0;
                    }
                    storageRecord.setMaterialId(storageMoveDetail0.getMaterialId());
                    storageRecord.setStorageId(storageMoveDetail0.getFrom());
                    storageRecordList = storageRecordMapper.selectStorageRecordList(storageRecord);
                    //原始库位
                    if (storageRecordList.size() == 1){
                        for (StorageRecord storageRecord0 : storageRecordList) {
                            storageRecord.setNumber(storageRecord0.getNumber() + storageMoveDetail0.getNumber());
                            storageRecordMapper.updateStorageRecord1(storageRecord);
                        }
                    }else if (storageRecordList.size() == 0){
                        storageRecord.setNumber(storageMoveDetail0.getNumber());
                        storageRecordMapper.batchStorageRecord(storageRecord);
                    }
                }
            }
        }
    }
}
