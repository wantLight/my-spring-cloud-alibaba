package com.itmuch.usercenter.service;


import com.itmuch.usercenter.domain.entity.user.User;

public interface DubboService {

    User findById(Integer id);
}
