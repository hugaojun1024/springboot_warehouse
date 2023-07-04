package com.zds.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zds.springboot.common.Constants;
import com.zds.springboot.common.Result;
import com.zds.springboot.model.FeedBack;
import com.zds.springboot.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed_back")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping
    public Result saveFeedBack(@RequestBody FeedBack feedBack){
        if (feedBackService.saveFeedBack(feedBack)){
            return Result.success();
        }
        else{
            return Result.success(Constants.CODE_WRONG_SYSTEM,"出错了，反馈建议失败");
        }

    }

    //分页查询 mybatis-plus方式
    @GetMapping("/page")
    Page<FeedBack> findPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam(defaultValue = "") String feedBackId, //设置默认值为空
                             @RequestParam(defaultValue = "") String dormBuilding,
                             @RequestParam(defaultValue = "") String dormRoom,
                             @RequestParam(defaultValue = "") String orderStatus){
        Page<FeedBack> page = new Page<>(pageNum,pageSize);
        QueryWrapper<FeedBack> queryWrapper = new QueryWrapper<>();
        if (!"".equals(feedBackId)){
            queryWrapper.like("feed_back_id",feedBackId);
        }
        if (!"".equals(dormBuilding)){
            queryWrapper.like("dorm_building",dormBuilding);
        }
        if (!"".equals(dormRoom)){
            queryWrapper.like("dorm_room",dormRoom);
        }
        if (!"".equals(orderStatus)){
            queryWrapper.eq("order_status",orderStatus);
        }
        queryWrapper.orderByDesc("submit_time"); //根据提交的时间排序
        //queryWrapper.or().like("address",address); //后面拼接or 条件
        Page<FeedBack> userPage = feedBackService.page(page, queryWrapper);
        return userPage;
    }
}
