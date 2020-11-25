package com.dcm.application.controller;

import com.dcm.application.domain.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yaoximing
 * @date : 2020-11-20
 * @description :
 **/
@RestController
public class TestController {

    @Autowired
    private DomainService domainService;

    @GetMapping("test")
    public String test() {
        return "hello";
    }

    @GetMapping("testI")
    public String testI() {
        domainService.saveDept();
        return "ok";
    }


}
