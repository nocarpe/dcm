package com.dcm.application.dal.mapper;

import com.dcm.application.dal.model.Dept;
import com.dcm.application.dal.model.Tel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yaoximing
 * @since 2020-11-20
 */
public interface TelMapper extends BaseMapper<Tel> {


    @Select("select t1.name from tbl_dept t1 LEFT JOIN tbl_tel t2 on t1.no = t2.phone")
    List<String> query();
}
