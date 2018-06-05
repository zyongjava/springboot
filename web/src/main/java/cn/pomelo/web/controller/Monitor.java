package cn.pomelo.web.controller;

import cn.pomelo.biz.service.intf.UserService;
import cn.pomelo.dal.mysql.object.UserDO;
import cn.pomelo.web.object.SessionUser;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 网页访问 <br/>
 * Created by zhengyong on 16/8/30.
 */
@Controller
public class Monitor extends BaseController{

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

    @RequestMapping(value = "/get")
    @ResponseBody
    public String getList() {
        SessionUser sessionUser = super.getSessionUser();
        System.out.println(JSON.toJSONString(sessionUser));
        List<UserDO> userDOList = userService.queryByParam(sessionUser.getName(), sessionUser.getAge());
        return String.format("get result = %s.", JSON.toJSONString(userDOList));
    }

}
