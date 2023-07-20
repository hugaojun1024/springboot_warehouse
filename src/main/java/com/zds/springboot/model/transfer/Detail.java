package com.zds.springboot.model.transfer;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_transfer_detail")
public class Detail {
    private String detailId;
    private String transferId;
    private String matCode;
    private String matName;
    private Integer transferNum;
    private String brand;
    private String matSpecifi;
}
