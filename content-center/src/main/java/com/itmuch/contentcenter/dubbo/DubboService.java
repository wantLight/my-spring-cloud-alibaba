package com.itmuch.contentcenter.dubbo;

import com.itmuch.contentcenter.dao.User;

public interface DubboService {

    User findById(Integer id);
}
