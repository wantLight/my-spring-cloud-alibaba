package com.itmuch.contentcenter.sentineltest;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * 处理授权来源
 *
 * 在授权里面添加 origin = xxxx 屏蔽比如说手机端访问。
 *
 *
 * 以上本质都是 CommonFilter
 */
//@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 从请求参数中获取名为 origin 的参数并返回
        // 如果获取不到origin参数，那么就抛异常

        // origin放到header中比较好
        String origin = request.getParameter("origin");
        if (StringUtils.isBlank(origin)) {
            throw new IllegalArgumentException("origin must be specified");
        }
        return origin;
    }
}
