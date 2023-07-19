package com.zds.springboot.model;

/**
 * Copyright 2023 json.cn
 */
import com.baomidou.mybatisplus.annotation.TableName;
import com.itextpdf.text.pdf.PRIndirectReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Auto-generated: 2023-06-27 16:46:17
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_main")
public class Main {

    private String MainId;
    private String purcOrderId;
    private String contractName;
    private int arrivalNum;
    private Date endTime;
    private String warehouseNo;
    private String arrivalDate;
    private String recCreator;
}
