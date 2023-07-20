package com.zds.springboot.model.transfer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_transfer")
public class Transfer {

    private String transferId;
    private String mainId;
    @TableField(exist = false)
    private Main main;
    @TableField(exist = false)
    private List<Detail> detail;

}
