package com.ssm.system.controller;

import com.ssm.system.entity.SysUser;
import com.ssm.system.service.SysUserService;
import com.ssm.system.vo.req.SysUserReqVo;
import com.ssm.system.vo.resp.SysUserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户管理类")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @PostMapping("/get")
    @ApiOperation(value = "根据ID获取用户信息")
    public SysUser selectByPrimaryKey(String id) {
        return sysUserService.selectByPrimaryKey(id);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    public String register(@RequestBody SysUserReqVo vo){
        return sysUserService.register(vo);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public SysUserRespVo login(@RequestBody SysUserReqVo vo){
        return sysUserService.login(vo);
    }
}
