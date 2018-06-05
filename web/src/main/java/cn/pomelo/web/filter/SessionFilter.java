package cn.pomelo.web.filter;

import cn.pomelo.biz.service.session.RedisSessionUtil;
import cn.pomelo.web.object.SessionAuthentication;
import cn.pomelo.web.object.SessionUser;
import cn.pomelo.web.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static cn.pomelo.web.constant.Constants.ALLOWED_PATHS;
import static cn.pomelo.web.constant.Constants.SESSION_KEY;

/**
 * session filter
 *
 * @author: zhengyong Date: 2018/5/28 Time: 上午11:10
 */
public class SessionFilter implements Filter {

    @Autowired
    private RedisSessionUtil redisSession;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String sessionId = CookieUtils.getCookieValue(request, SESSION_KEY);
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (StringUtils.isBlank(sessionId)) {
            sessionId = StringUtils.remove(UUID.randomUUID().toString(), "-");
            CookieUtils.setCookie(response, SESSION_KEY, sessionId);
        }
        // 往SecurityContext放session
        SessionAuthentication auth = new SessionAuthentication();
        SessionUser sessionUser = new SessionUser();
        sessionUser.setSessionId(sessionId);
        auth.inject(sessionUser);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 不需要session校验
        if (ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        sessionUser = (SessionUser) redisSession.getSession(sessionId);
        // 没有session返回错误页面
        if (sessionUser == null) {
            response.sendRedirect("/error/session");
            return;
        }
        // 获取登录ip
        String ip = request.getRemoteAddr();
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        sessionUser.setIpAddress(ip);
        filterChain.doFilter(request, response);

        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
