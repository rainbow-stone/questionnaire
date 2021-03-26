package com.baoshine.questionnaire.service;

import cn.hutool.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "${platform.url}", name = "platform")
public interface RemoteService {

    @RequestMapping(value = "platform-system/load/user", method = RequestMethod.GET)
    JSONObject getLoginUser(@RequestParam("token") String token);

}
