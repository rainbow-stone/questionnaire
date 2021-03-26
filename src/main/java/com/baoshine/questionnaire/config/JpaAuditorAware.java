package com.baoshine.questionnaire.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baoshine.questionnaire.service.RemoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class JpaAuditorAware implements AuditorAware<Integer> {

    @Resource
    private RemoteService remoteService;

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${token.header}")
    private String header;

    @Override
    public Optional<Integer> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(header);
        if (!StrUtil.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        JSONObject user = remoteService.getLoginUser(token);
        Integer userId = user.getJSONObject("user").getInt("userId");
        return Optional.of(userId);
    }
}
