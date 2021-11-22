package com.dcm.boot.temp;

import com.nlf.calendar.Lunar;
 
/**
 * 阴历示例
 *
 */
public class LunarSample{
  public static void main(String[] args){
    //今天
    //Lunar date = new Lunar();
     
    //指定阴历的某一天
    Lunar date = new Lunar(1999,10,07);
    System.out.println(date.toFullString());
    System.out.println(date.getSolar().toFullString());
  }
}
