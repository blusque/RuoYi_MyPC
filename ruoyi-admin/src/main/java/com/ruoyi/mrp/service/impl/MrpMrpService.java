package com.ruoyi.mrp.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.mrp.domain.MrpMps;
import com.ruoyi.system.domain.SysCraft;
import com.ruoyi.system.domain.SysMaterial;
import com.ruoyi.system.mapper.SysCraftMapper;
import com.ruoyi.system.mapper.SysMaterialMapper;
import com.ruoyi.mrp.service.IMrpMrpService;

public class MrpMrpService implements IMrpMrpService {
    private int maxNum;

    @Autowired
    private List<Long> nums;

    @Autowired
    private List<String> froms;

    @Autowired
    private SysCraftMapper sysCraftMapper;

    @Autowired
    private SysMaterialMapper sysMaterialMapper;

    MrpMrpService() {
        maxNum = sysMaterialMapper.selectMaxSysMaterialId().intValue();

        for (int i = 0; i < maxNum; ++i) {
            nums.add(Long.valueOf(0));
            SysMaterial material = sysMaterialMapper.selectSysMaterialById(Long.valueOf(i));
            if (material != null) {
                froms.set(i, material.getFrom());
            }
            else {
                froms.set(i, String.valueOf('0'));
            }
        }
    }

    @Override
    public void mrp(MrpMps mps) {
        Long parentId = mps.getProductId();
        Date ddl = mps.getProductTime();
        SysCraft parent = new SysCraft();
        parent.setParentId(parentId);  
        List<SysCraft> list = sysCraftMapper.selectSysCraftList(parent);
        for (SysCraft craft : list) {
            int childId = craft.getMaterialId().intValue();
            Long nowNum = nums.get(childId);
            nowNum += mps.getProductNumber();
            nums.set(childId, nowNum);
        }
        ask(ddl);
        reset();
    }

    public void reset() {
        for (int i = 0; i < maxNum; ++i) {
            nums.set(i, Long.valueOf(0));
        }
    }

    public void ask(Date ddl) {
        for (int i = 0; i < nums.size(); ++i) {
            
        }
    }
}
