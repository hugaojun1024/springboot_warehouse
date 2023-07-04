package com.zds.springboot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_feed_back")
public class FeedBack {
    @TableId(type = IdType.INPUT)
    private String feedBackId;

    private String feedBackPerson;

    private String dept;

    private String phone;

    private String Type;

    private String suggestion;

    private String problemDesc;

    private String supplement;

    @TableField(exist = false)
    private List<String> problemType;

    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone="GMT+8")
    private Date submitTime;

}
