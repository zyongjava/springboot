package cn.pomelo.web.controller;

import cn.pomelo.biz.service.session.RedisSessionUtil;
import cn.pomelo.web.object.SessionAuthentication;
import cn.pomelo.web.object.SessionUser;
import cn.pomelo.web.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static cn.pomelo.web.constant.Constants.SESSION_KEY;

/**
 * @author: zhengyong Date: 2018/5/28 Time: 下午12:08
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private RedisSessionUtil redisSession;

    @RequestMapping("/login")
    public void login(@RequestParam("userName") String userName , HttpServletResponse response, HttpServletRequest request) {
        String sessionId = super.getSessionId();
        SessionUser sessionUser = new SessionUser();
        sessionUser.setName(userName);
        redisSession.setSession(sessionId, sessionUser);
        System.out.println(userName);
        try {
            response.sendRedirect("/get");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login/out")
    public String loginOut(HttpServletResponse response, HttpServletRequest request) {
        String sessionId = super.getSessionId();
        redisSession.invalidateSession(sessionId);
        CookieUtils.removeCookie(request, response, SESSION_KEY);
        return "home";
    }

}
