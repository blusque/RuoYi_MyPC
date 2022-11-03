package com.ruoyi.sc.service;

import java.util.List;
import com.ruoyi.sc.domain.Dispatch;

/**
 * 派工单子Service接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IDispatchService 
{
    /**
     * 查询派工单子
     * 
     * @param mcnum 派工单子主键
     * @return 派工单子
     */
    public Dispatch selectDispatchByMcnum(Long mcnum);

    /**
     * 查询派工单子列表
     * 
     * @param dispatch 派工单子
     * @return 派工单子集合
     */
    public List<Dispatch> selectDispatchList(Dispatch dispatch);

    /**
     * 新增派工单子
     * 
     * @param dispatch 派工单子
     * @return 结果
     */
    public int insertDispatch(Dispatch dispatch);

    /**
     * 修改派工单子
     * 
     * @param dispatch 派工单子
     * @return 结果
     */
    public int updateDispatch(Dispatch dispatch);

    /**
     * 批量删除派工单子
     * 
     * @param mcnums 需要删除的派工单子主键集合
     * @return 结果
     */
    public int deleteDispatchByMcnums(Long[] mcnums);

    /**
     * 删除派工单子信息
     * 
     * @param mcnum 派工单子主键
     * @return 结果
     */
    public int deleteDispatchByMcnum(Long mcnum);
}
