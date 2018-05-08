package cn.pomelo.web.controller;

import cn.pomelo.biz.service.intf.UserService;
import cn.pomelo.dal.mysql.object.UserDO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 网页访问 <br/>
 * Created by zhengyong on 16/8/30.
 */
@RestController
public class Monitor {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ok.htm")
    @ResponseBody
    public String ok() {
        return "ok";
    }

    @RequestMapping(value = "/get")
    public String getList(String name, Integer age) {
        List<UserDO> userDOList = userService.queryByParam(name, age);

        return String.format("get result = %s.", JSON.toJSONString(userDOList));
    }

}
