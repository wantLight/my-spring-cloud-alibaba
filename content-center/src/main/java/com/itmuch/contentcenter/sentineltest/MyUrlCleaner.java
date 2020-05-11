package com.itmuch.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 支持restful api
 * 重新定义资源名称
 *
 * 以上本质都是 CommonFilter  基于这个可以自定义Filter
 *
 */
@Component
@Slf4j
public class MyUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        // 让 /shares/1 与 /shares/2 的返回值相同
        // 返回/shares/{number}

        String[] split = originUrl.split("/");

        return Arrays.stream(split)
            .map(string -> {
                if (NumberUtils.isNumber(string)) {
                    return "{number}";
                }
                return string;
            })
            .reduce((a, b) -> a + "/" + b)
            .orElse("");
    }
}
