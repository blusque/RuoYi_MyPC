package com.ruoyi.sc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sc.mapper.DispatchMapper;
import com.ruoyi.sc.domain.Dispatch;
import com.ruoyi.sc.service.IDispatchService;

/**
 * 派工单子Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class DispatchServiceImpl implements IDispatchService 
{
    @Autowired
    private DispatchMapper dispatchMapper;

    /**
     * 查询派工单子
     * 
     * @param mcnum 派工单子主键
     * @return 派工单子
     */
    @Override
    public Dispatch selectDispatchByMcnum(Long mcnum)
    {
        return dispatchMapper.selectDispatchByMcnum(mcnum);
    }

    /**
     * 查询派工单子列表
     * 
     * @param dispatch 派工单子
     * @return 派工单子
     */
    @Override
    public List<Dispatch> selectDispatchList(Dispatch dispatch)
    {
        return dispatchMapper.selectDispatchList(dispatch);
    }

    /**
     * 新增派工单子
     * 
     * @param dispatch 派工单子
     * @return 结果
     */
    @Override
    public int insertDispatch(Dispatch dispatch)
    {
        return dispatchMapper.insertDispatch(dispatch);
    }

    /**
     * 修改派工单子
     * 
     * @param dispatch 派工单子
     * @return 结果
     */
    @Override
    public int updateDispatch(Dispatch dispatch)
    {
        return dispatchMapper.updateDispatch(dispatch);
    }

    /**
     * 批量删除派工单子
     * 
     * @param mcnums 需要删除的派工单子主键
     * @return 结果
     */
    @Override
    public int deleteDispatchByMcnums(Long[] mcnums)
    {
        return dispatchMapper.deleteDispatchByMcnums(mcnums);
    }

    /**
     * 删除派工单子信息
     * 
     * @param mcnum 派工单子主键
     * @return 结果
     */
    @Override
    public int deleteDispatchByMcnum(Long mcnum)
    {
        return dispatchMapper.deleteDispatchByMcnum(mcnum);
    }
}
