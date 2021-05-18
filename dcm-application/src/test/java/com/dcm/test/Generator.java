package com.dcm.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author : yaoximing
 * @date : 2020/4/2
 * @description : mapper生成器
 **/
public class Generator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D://generator");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(true);
        gc.setAuthor("yaoximing");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("ddmc");
        dsc.setPassword("ddmc@123");
        dsc.setUrl(
                "jdbc:mysql://10.23.30.125:3306/dcm1?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("tbl_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"tbl_config"}); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        //        strategy.setSuperEntityClass("top.ibase4j.core.base.BaseModel");
        // 自定义实体，公共字段
        //        strategy.setSuperEntityColumns(
        //                new String[] { "id_", "enable_", "remark_", "create_by", "create_time", "update_by", "update_time" });
        // 自定义 mapper 父类
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 自定义 service 父类(生成后请把Service的父类删除)
        // 自定义 service 实现类父类
        //strategy.setSuperServiceImplClass("");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("");
        //生成 <code>@RestController</code> 控制器
        strategy.setRestControllerStyle(true);
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        //        strategy.setLogicDeleteFieldName("enable");
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(false);
        mpg.setStrategy(strategy);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        //        InjectionConfig cfg = new InjectionConfig() {
        //            @Override
        //            public void initMap() {
        //                Map<String, Object> map = new HashMap<String, Object>();
        //                //                map.put("providerClass", "IBizProvider");
        //                //                map.put("providerClassPackage", "org.xshop.provider.IBizProvider");
        //                //map.put("rpcService", false);
        //                this.setMap(map);
        //            }
        //        };
        //        mpg.setCfg(cfg);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dcm.application.dal");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setXml("sqlmap/dcm");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setController("controller");
        mpg.setPackageInfo(pc);
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        //        TemplateConfig tc = new TemplateConfig();
        //        tc.setEntity("tpl/entity.java.vm");
        //        tc.setMapper("tpl/mapper.java.vm");
        //        tc.setXml("tpl/mapper.xml.vm");
        //        tc.setService("tpl/iservice.java.vm");
        //        tc.setServiceImpl("tpl/service.java.vm");
        //        tc.setController("tpl/controller.java.vm");
        //        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}
