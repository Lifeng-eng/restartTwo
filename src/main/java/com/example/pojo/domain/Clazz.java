package com.example.pojo.domain;



import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.common.Audit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 班级表
* &#064;TableName  clazz
 */

@Data
public class Clazz implements Serializable, Audit {

    /**
    * ID,主键
    */
    @NotNull(message="[ID,主键]不能为空")
    private Integer id;
    /**
    * 班级名称
    */
    @NotBlank(message="[班级名称]不能为空")
    @Size(max= 30,message="编码长度不能超过30")
    @Length(max= 30,message="编码长度不能超过30")
    private String name;
    /**
    * 班级教室
    */
    @Size(max= 20,message="编码长度不能超过20")
    @Length(max= 20,message="编码长度不能超过20")
    private String room;
    /**
    * 开课时间
    */
    @NotNull(message="[开课时间]不能为空")
    private Date beginDate;
    /**
    * 结课时间
    */
    @NotNull(message="[结课时间]不能为空")
    private Date endDate;
    /**
    * 班主任ID, 关联员工表ID
    */
    private Integer masterId;
    /**
    * 学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式
    */
    @NotNull(message="[学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式]不能为空")
    private Integer subject;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;



}
