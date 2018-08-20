package cn.pomelo.web.controller;

import cn.pomelo.biz.service.intf.UserService;
import cn.pomelo.dal.mysql.object.UserDO;
import cn.pomelo.web.object.SessionUser;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 网页访问 <br/>
 * Created by zhengyong on 16/8/30.
 */
@Controller
@Api(tags = "应用监控API")
public class Monitor extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ok.htm", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "应用ok")
    public String ok() {
        return "ok";
    }

    @RequestMapping(value = "/error/session", method = RequestMethod.GET)
    @ApiOperation(value = "用户未登录")
    public String auth() {
        return "noSession";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户列表")
    public String getList() {
        SessionUser sessionUser = super.getSessionUser();
        System.out.println(JSON.toJSONString(sessionUser));
        List<UserDO> userDOList = userService.queryByParam(sessionUser.getName(), sessionUser.getAge());
        return String.format("get result = %s.", JSON.toJSONString(userDOList));
    }

}
