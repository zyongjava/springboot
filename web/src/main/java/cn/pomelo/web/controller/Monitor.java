package cn.pomelo.web.controller;

import cn.pomelo.biz.service.intf.UserService;
import cn.pomelo.dal.mysql.object.UserDO;
import cn.pomelo.web.object.SessionAuthentication;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 网页访问 <br/>
 * Created by zhengyong on 16/8/30.
 */
@Controller
public class Monitor {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ok.htm")
    @ResponseBody
    public String ok() {
        return "ok";
    }

    @RequestMapping(value = "/error/session")
    public String auth() {
        return "noSession";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String getList(Integer age) {

        // filter context session
        SessionAuthentication authentication = (SessionAuthentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        List<UserDO> userDOList = userService.queryByParam(authentication.getName(), age);

        return String.format("get result = %s.", JSON.toJSONString(userDOList));
    }

}
