package cn.pomelo.biz.service.intf;

import cn.pomelo.dal.mysql.object.UserDO;

import java.util.List;

/**
 * Created by yong.zheng on 16/8/22.
 */
public interface UserService {

    /**
     * 查询用户
     *
     * @param name 用户姓名
     * @param age 用户年龄
     * @return list
     */
    List<UserDO> queryByParam(String name, Integer age);

    /**
     * 插入数据
     *
     * @param userDO
     * @return int
     */
    int insert(UserDO userDO);
}
