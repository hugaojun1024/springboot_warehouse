package com.zds.springboot.model;

/**
 * Copyright 2023 json.cn
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto-generated: 2023-06-27 16:46:17
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_detail")
public class Detail {

    private String detailId;

    private String messageId;

    private String purcMatSpecifi;
    private int arrivalNumber;
    private String purcBrand;
    private String matCode;
    private String matName;

}
