package cn.pomelo.dal.mysql.dao;

import java.util.List;

import cn.pomelo.dal.mysql.object.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户数据库层
 */
public interface UserDao {

    /**
     * 查询用户
     * 
     * @param name 用户姓名
     * @param age 用户年龄
     * @return list
     */
    List<UserDO> queryByParam(@Param("name") String name, @Param("age") Integer age);

    /**
     * 插入数据
     * 
     * @param userDO
     * @return int
     */
    int insert(UserDO userDO);
}
