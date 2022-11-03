package com.ruoyi.sc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sc.mapper.PickingListItemsMapper;
import com.ruoyi.sc.domain.PickingListItems;
import com.ruoyi.sc.service.IPickingListItemsService;

/**
 * 领料单物料Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class PickingListItemsServiceImpl implements IPickingListItemsService 
{
    @Autowired
    private PickingListItemsMapper pickingListItemsMapper;

    /**
     * 查询领料单物料
     * 
     * @param mrnum 领料单物料主键
     * @return 领料单物料
     */
    @Override
    public PickingListItems selectPickingListItemsByMrnum(Long mrnum)
    {
        return pickingListItemsMapper.selectPickingListItemsByMrnum(mrnum);
    }

    /**
     * 查询领料单物料列表
     * 
     * @param pickingListItems 领料单物料
     * @return 领料单物料
     */
    @Override
    public List<PickingListItems> selectPickingListItemsList(PickingListItems pickingListItems)
    {
        return pickingListItemsMapper.selectPickingListItemsList(pickingListItems);
    }

    /**
     * 新增领料单物料
     * 
     * @param pickingListItems 领料单物料
     * @return 结果
     */
    @Override
    public int insertPickingListItems(PickingListItems pickingListItems)
    {
        return pickingListItemsMapper.insertPickingListItems(pickingListItems);
    }

    /**
     * 修改领料单物料
     * 
     * @param pickingListItems 领料单物料
     * @return 结果
     */
    @Override
    public int updatePickingListItems(PickingListItems pickingListItems)
    {
        return pickingListItemsMapper.updatePickingListItems(pickingListItems);
    }

    /**
     * 批量删除领料单物料
     * 
     * @param mrnums 需要删除的领料单物料主键
     * @return 结果
     */
    @Override
    public int deletePickingListItemsByMrnums(Long[] mrnums)
    {
        return pickingListItemsMapper.deletePickingListItemsByMrnums(mrnums);
    }

    /**
     * 删除领料单物料信息
     * 
     * @param mrnum 领料单物料主键
     * @return 结果
     */
    @Override
    public int deletePickingListItemsByMrnum(Long mrnum)
    {
        return pickingListItemsMapper.deletePickingListItemsByMrnum(mrnum);
    }
}
