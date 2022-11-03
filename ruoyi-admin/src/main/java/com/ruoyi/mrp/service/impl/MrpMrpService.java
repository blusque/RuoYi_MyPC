package com.ruoyi.mrp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.mrp.domain.MrpMps;
import com.ruoyi.system.domain.SysCraft;
import com.ruoyi.system.mapper.SysCraftMapper;
import com.ruoyi.mrp.service.IMrpMrpService;

public class MrpMrpService implements IMrpMrpService {
    @Autowired
    List<Long> nums;

    @Autowired
    List<Integer> froms;

    @Autowired
    SysCraftMapper sysCraftMapper;

    @Override
    public void mrp(MrpMps mps) {
        initMrp();
        Long parentId = mps.getProductId();
        SysCraft parent = new SysCraft();
        parent.setParentId(parentId);  
        List<SysCraft> list = sysCraftMapper.selectSysCraftList(parent);
        for (SysCraft craft : list) {
            int childId = craft.getMaterialId().intValue();
            Long nowNum = nums.get(childId);
            nowNum += mps.getProductNumber();
            nums.set(childId, nowNum);
        }
        ask();
    }

    public void initMrp() {

    }

    public void ask() {

    }
}
