package com.zds.springboot.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "t_user")
public class User {

    @TableId(type = IdType.INPUT)
    private String userId;

    private String username;

    private String userPassword;

    private Integer role;

    private String nickname;

    private String avatarUrl;

    private Date createTime;

}
