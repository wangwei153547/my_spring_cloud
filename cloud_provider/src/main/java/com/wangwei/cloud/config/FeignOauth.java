package com.wangwei.cloud.config;

import com.wangwei.cloud.sys.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oauth2-server")
public interface FeignOauth {
    @PostMapping("/oauth/me")
    public User getUser(@RequestParam String access_token);
}
