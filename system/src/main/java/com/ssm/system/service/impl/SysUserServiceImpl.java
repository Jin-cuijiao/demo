package com.ssm.system.service.impl;

import com.ssm.system.entity.SysUser;
import com.ssm.system.exception.BusinessException;
import com.ssm.system.mapper.SysUserMapper;
import com.ssm.system.service.RedisService;
import com.ssm.system.service.SysUserService;
import com.ssm.system.utils.PasswordEncoder;
import com.ssm.system.utils.PasswordUtils;
import com.ssm.system.vo.req.SysUserReqVo;
import com.ssm.system.vo.resp.SysUserRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    RedisService redisService;
    @Override
    public SysUser selectByPrimaryKey(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public String register(SysUserReqVo vo){
        SysUser sysUser =new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(vo.getPassword(),sysUser.getSalt());
        sysUser.setPassword(encode);
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());
        int i=sysUserMapper.insertSelective(sysUser);
        if(i!=1){
            return "注册失败";
        }else {
            return "注册成功";
        }
    }
    @Override
    public SysUserRespVo login(SysUserReqVo vo){
        SysUser sysUser =sysUserMapper.getselectByPrimaryKey(vo.getUsername());
        if(sysUser==null){
            throw new BusinessException(4001004,"该用户不存在");
        }
        if(sysUser.getStatus()==2){
            throw new BusinessException(4001004,"该用户已被禁用请联系系统管理员");
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),vo.getPassword(),sysUser.getPassword())){
            throw new BusinessException(4001004,"密码不匹配");
        }
        SysUserRespVo respVo = new SysUserRespVo();
        respVo.setToken(UUID.randomUUID().toString());
        respVo.setId(sysUser.getId());
        redisService.set(respVo.getToken(),respVo.getId(),60, TimeUnit.MINUTES);
        return respVo;
    }
}
