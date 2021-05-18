package com.dcm.application.domain;

import com.dcm.application.dal.mapper.DeptMapper;
import com.dcm.application.dal.mapper.TelMapper;
import com.dcm.application.dal.model.Dept;
import com.dcm.application.dal.model.Tel;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : yaoximing
 * @date : 2020-11-23
 * @description :
 **/
@Service
public class DomainService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private TelMapper telMapper;

    public void saveDept() {

        for (long i = 1; i <= 100; i++) {
            Dept dept = new Dept();
            dept.setNo(i);
            dept.setName("测试" + i);
            dept.setCreateTime(LocalDateTime.now());
            deptMapper.insert(dept);
            System.out.println(dept.getId()+"============");
        }

    }

    public void saveTel() {

        for (long i = 1; i <= 100; i++) {
            Tel tel = new Tel();
            tel.setPhone(i);
            tel.setName("测试" + i);
            tel.setCreateTime(LocalDateTime.now());
            telMapper.insert(tel);
        }

    }


}
