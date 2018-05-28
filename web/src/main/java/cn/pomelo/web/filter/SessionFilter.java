package cn.pomelo.web.filter;

import cn.pomelo.web.object.SessionAuthentication;
import cn.pomelo.web.object.SessionUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static cn.pomelo.web.constant.Constants.ALLOWED_PATHS;
import static cn.pomelo.web.constant.Constants.SESSION_KEY;

/**
 * session filter
 *
 * @author: zhengyong Date: 2018/5/28 Time: 上午11:10
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 不需要session校验
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (ALLOWED_PATHS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION_KEY);
        // 没有session返回错误页面
        if (sessionUser == null) {
            response.sendRedirect("/error/session");
            return;
        }

        // 往SecurityContext放session
        SessionAuthentication auth = new SessionAuthentication();
        // 获取登录ip
        String ip = request.getRemoteAddr();
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        sessionUser.setIpAddress(ip);
        auth.inject(sessionUser);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
