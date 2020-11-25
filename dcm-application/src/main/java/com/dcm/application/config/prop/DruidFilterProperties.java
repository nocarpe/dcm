package com.dcm.application.config.prop;

import com.alibaba.druid.wall.WallConfig;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
    prefix = "dcm.druid.filter"
)
public class DruidFilterProperties extends MybatisPlusProperties {

    private Boolean enableWallFilter = true;
    private WallConfig wall = new WallConfig();

    public DruidFilterProperties() {
    }

    public Boolean getEnableWallFilter() {
        return this.enableWallFilter;
    }

    public WallConfig getWall() {
        return this.wall;
    }

    public void setEnableWallFilter(final Boolean enableWallFilter) {
        this.enableWallFilter = enableWallFilter;
    }

    public void setWall(final WallConfig wall) {
        this.wall = wall;
    }
}