package com.dcm.test.domain;

import com.dcm.application.dal.mapper.ConfigMapper;
import com.dcm.application.dal.mapper.TelMapper;
import com.dcm.application.dal.model.Config;
import com.dcm.application.dal.model.Dept;
import com.dcm.application.dal.model.Tel;
import com.dcm.application.domain.DomainService;
import com.dcm.test.MainTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : yaoximing
 * @date : 2020-11-30
 * @description :
 **/
public class QueryTest extends MainTest {

    @Autowired
    private TelMapper telMapper;
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private DomainService domainService;
    @Test
    public void testq() {
        List<String> telList = telMapper.query();
        System.out.println(telList.size());
    }

    @Test
    public  void testc(){
     List<String> configs =    configMapper.query();
        System.out.println(configs.size());
    }


    @Test
    public  void test2(){
        domainService.saveDept();
    }

}
