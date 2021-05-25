package com.dcm.application.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcm.application.dal.model.Config;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yaoximing
 * @since 2020-11-30
 */
public interface ConfigMapper extends BaseMapper<Config> {

    @Select("select t1.name from tbl_config t1 left join tbl_dept t2 on t1.name= t2.name")
    List<String> query();
}
