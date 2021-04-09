package com.baoshine.questionnaire.config;

import com.baoshine.questionnaire.service.RemoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class JpaAuditorAware implements AuditorAware<String> {

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    @Resource
    private RemoteService remoteService;
    @Value("${token.header}")
    private String header;

    @Override
    public Optional<String> getCurrentAuditor() {
        /*ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(header);
        if (!StrUtil.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        JSONObject user = remoteService.getLoginUser(token);
        Integer userId = user.getJSONObject("user").getInt("userId");
        return Optional.of(userId);*/
        return Optional.of("1");
    }
}
