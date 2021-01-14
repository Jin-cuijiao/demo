package com.ssm.system.vo.req;

import lombok.Data;

@Data
public class SysUserReqVo {
    private String username;

    private String password;

    private String phone;


    private String email;

    private Byte sex;

    private Byte createWhere;
}
