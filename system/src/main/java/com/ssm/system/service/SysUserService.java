package com.ssm.system.service;

import com.ssm.system.entity.SysUser;
import com.ssm.system.vo.req.SysUserReqVo;
import com.ssm.system.vo.resp.SysUserRespVo;

public interface SysUserService {
    SysUser selectByPrimaryKey(String id);

    String register(SysUserReqVo vo);

    SysUserRespVo login(SysUserReqVo vo);
}
