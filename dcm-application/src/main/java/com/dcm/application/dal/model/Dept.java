package com.dcm.application.dal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yaoximing
 * @since 2020-11-20
 */
@TableName("tbl_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Long no;

    private String name;

    private LocalDateTime createTime;

    
    public Long getId() {
        return id;
    }

      public void setId(Long id) {
          this.id = id;
      }
    
    public Long getNo() {
        return no;
    }

      public void setNo(Long no) {
          this.no = no;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }

    @Override
    public String toString() {
        return "Dept0{" +
              "id=" + id +
                  ", no=" + no +
                  ", name=" + name +
                  ", createTime=" + createTime +
              "}";
    }
}
