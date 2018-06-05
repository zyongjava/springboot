package cn.pomelo.web.controller;

import cn.pomelo.web.object.SessionAuthentication;
import cn.pomelo.web.object.SessionUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: zhengyong Date: 2018/6/5 Time: 上午9:49
 */
public class BaseController {

    /**
     * 获取sessionId
     *
     * @return sessionId
     */
    protected String getSessionId() {
        SessionAuthentication authentication = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

    /**
     * 获取session对象
     *
     * @return SessionUser
     */
    protected SessionUser getSessionUser() {
        SessionAuthentication authentication = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return (SessionUser) authentication.getDetails();
    }
}
