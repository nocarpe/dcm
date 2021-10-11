package com.dcm.thread.test;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : yaoximing
 * @date : 2021-05-20
 * @description :
 **/
public class Test {
    public static final String TIME_FORMAT = "HH:mm";
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        DES des = SecureUtil.des(Base64.getDecoder().decode("pHBGusspFeo="));
       System.out.println(des.decryptStr(""));
    }
}
