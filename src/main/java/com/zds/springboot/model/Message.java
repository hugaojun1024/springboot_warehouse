package com.zds.springboot.model;

/**
 * Copyright 2023 json.cn
 */
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Auto-generated: 2023-06-27 16:46:17
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_message")
public class Message {

    private String messageId;
    private String mainId;
    @TableField(exist = false)
    private Main main;
    @TableField(exist = false)
    private List<Detail> detail;

}
