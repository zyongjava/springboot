package cn.pomelo.web.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 静态常量
 */
public interface Constants {

    /**
     * session key
     */
    String SESSION_KEY = "JSESSIONID";

    /**
     * 不需要权限拦截地址
     */
    Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "/error/session",
            "/login",
            "/ok.htm",
            "/login/out", "/favicon.ico", "")));
}
