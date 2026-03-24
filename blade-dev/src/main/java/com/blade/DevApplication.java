package com.blade;

import com.hellokaton.blade.Blade;
import com.hellokaton.blade.options.HttpOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DevApplication {

    public static void main(String[] args) {
        Blade.create()
                .scanPackages("com.blade.dev.controller")  // 扫描 Controller 包
                .http(HttpOptions::enableSession)    // 启用会话
                .start(DevApplication.class, args);
    }
}
