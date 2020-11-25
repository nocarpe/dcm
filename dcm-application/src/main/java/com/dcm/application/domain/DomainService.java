package com.dcm.application.domain;

import com.dcm.application.dal.mapper.DeptMapper;
import com.dcm.application.dal.model.Dept;
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


    public void saveDept() {

        for (long i = 1; i <= 10; i++) {
            Dept dept = new Dept();
            dept.setNo(i);
            dept.setName("测试" + i);
            dept.setCreateTime(LocalDateTime.now());
            deptMapper.insert(dept);
        }

    }
}
