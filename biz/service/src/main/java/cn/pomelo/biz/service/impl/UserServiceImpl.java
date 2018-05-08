package cn.pomelo.biz.service.impl;

import cn.pomelo.biz.service.intf.UserService;
import cn.pomelo.dal.mysql.dao.UserDao;
import cn.pomelo.dal.mysql.object.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yong.zheng on 16/8/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao             userDao;

    @Override
    public List<UserDO> queryByParam(String name, Integer age) {

        return userDao.queryByParam(name, age);
    }

    @Override
    public int insert(UserDO userDO) {
        if (userDO == null) {
            logger.warn("insert data error. the userDO is null.");
            return -1;
        }
        return userDao.insert(userDO);
    }
}
