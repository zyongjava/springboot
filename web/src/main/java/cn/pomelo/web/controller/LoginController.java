package cn.pomelo.web.controller;

import cn.pomelo.web.object.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static cn.pomelo.web.constant.Constants.SESSION_KEY;

/**
 * @author: zhengyong Date: 2018/5/28 Time: 下午12:08
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public void login(@RequestParam("userName") String userName , HttpSession httpSession, HttpServletResponse response) {
        SessionUser sessionUser = new SessionUser();
        sessionUser.setName(userName);
        httpSession.setAttribute(SESSION_KEY, sessionUser);
        System.out.println(userName);
        try {
            response.sendRedirect("/get");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login/out")
    public String loginOut(HttpSession httpSession) {
        httpSession.invalidate();
        return "home";
    }

}
