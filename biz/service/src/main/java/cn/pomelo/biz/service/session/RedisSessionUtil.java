package cn.pomelo.biz.service.session;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟redis session 操作
 *
 * @author: zhengyong Date: 2018/6/4 Time: 下午6:32
 */
@Component("redisSession")
public class RedisSessionUtil {

    private Map<String, Object> session = new HashMap<>();

    public boolean setSession(String key, Object value) {
        session.put(key, value);
        return true;
    }

    public Object getSession(String key) {
        return session.get(key);
    }

    public boolean invalidateSession(String key) {
        session.remove(key);
        return true;
    }
}
