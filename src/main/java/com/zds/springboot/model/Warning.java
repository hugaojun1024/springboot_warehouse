package com.zds.springboot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_warning")
public class Warning {
    @TableId(type = IdType.INPUT)
    private String warningId;
    private String batchNo;
    private Integer warningNum;

}
