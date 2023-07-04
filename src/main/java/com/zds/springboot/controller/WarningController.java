package com.zds.springboot.controller;

import com.zds.springboot.common.Result;
import com.zds.springboot.model.Warning;
import com.zds.springboot.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warning")
public class WarningController {

    @Autowired
    private WarningService warningService;

    @RequestMapping("/set_warning_num")
    public Result setWarningNum(@RequestBody Warning warning){
        warningService.saveWarning(warning);
        return Result.success("设置成功",null);
    }
}
