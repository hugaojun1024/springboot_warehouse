package com.zds.springboot.service;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.springboot.common.Constants;
import com.zds.springboot.exception.ServiceException;
import com.zds.springboot.mapper.UserMapper;
import com.zds.springboot.model.User;
import com.zds.springboot.utils.EncryptBySHA256;
import com.zds.springboot.utils.IDWorker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public User login(User user) {
        User u = null;
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getUserPassword())){
            throw new ServiceException(Constants.CODE_NULL_USERNAME_OR_PASSWORD,"输入的用户名或密码为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        // 加密密码
        EncryptBySHA256 encryptBySHA256 = new EncryptBySHA256();
        String psw = encryptBySHA256.getSHA256StrJava(user.getUserPassword());
        queryWrapper.eq("user_password",psw);
        List<User> list = list(queryWrapper);
        if (list.size() == 0){
            //登陆失败
            throw new ServiceException(Constants.CODE_WRONG_USERNAME_OR_PASSWORD,"输入的用户名或密码错误，登陆失败");
        }
        u = list.get(0);
        return u;
    }

    // 注册或更新用户信息
    public boolean saveUser(User user) {
        if (user.getUserId() == null){
            // 注册新用户
            // 生成id
            IDWorker idWorker = new IDWorker(1, 1, 1);
            user.setUserId(idWorker.nextStringId());
            //密码加密
            EncryptBySHA256 encryptBySHA256 = new EncryptBySHA256();
            String psw = encryptBySHA256.getSHA256StrJava(user.getUserPassword());
            user.setUserPassword(psw);
            return save(user);
        }
        else{
            // 更新用户信息
            return updateById(user);
        }
    }
}
