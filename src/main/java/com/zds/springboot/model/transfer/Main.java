package com.zds.springboot.model.transfer;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_transfer_main")
public class Main {
    private String mainId;
    private String transferNo;
    private Integer num;
}
