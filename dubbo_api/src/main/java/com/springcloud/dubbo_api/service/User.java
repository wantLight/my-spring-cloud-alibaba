package com.springcloud.dubbo_api.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    /**
     * Id
     */

    private Integer id;

    /**
     * 微信id
     */

    private String wxId;

    /**
     * 微信昵称
     */

    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */

    private String avatarUrl;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 修改时间
     */

    private Date updateTime;

    /**
     * 积分
     */
    private Integer bonus;
}