package com.ruoyi.sc.mapper;

import java.util.List;
import com.ruoyi.sc.domain.PickingListItems;

/**
 * 领料单物料Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface PickingListItemsMapper 
{
    /**
     * 查询领料单物料
     * 
     * @param mrnum 领料单物料主键
     * @return 领料单物料
     */
    public PickingListItems selectPickingListItemsByMrnum(Long mrnum);

    /**
     * 查询领料单物料列表
     * 
     * @param pickingListItems 领料单物料
     * @return 领料单物料集合
     */
    public List<PickingListItems> selectPickingListItemsList(PickingListItems pickingListItems);

    /**
     * 新增领料单物料
     * 
     * @param pickingListItems 领料单物料
     * @return 结果
     */
    public int insertPickingListItems(PickingListItems pickingListItems);

    /**
     * 修改领料单物料
     * 
     * @param pickingListItems 领料单物料
     * @return 结果
     */
    public int updatePickingListItems(PickingListItems pickingListItems);

    /**
     * 删除领料单物料
     * 
     * @param mrnum 领料单物料主键
     * @return 结果
     */
    public int deletePickingListItemsByMrnum(Long mrnum);

    /**
     * 批量删除领料单物料
     * 
     * @param mrnums 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePickingListItemsByMrnums(Long[] mrnums);
}
