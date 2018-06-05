package cn.pomelo.web.object;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author: zhengyong Date: 2018/5/28 Time: 上午11:30
 */
public class SessionAuthentication implements Authentication {

    private SessionUser sessionUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void inject(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public Object getCredentials() {
        if (!isAuthenticated()) return null;
        return sessionUser.getIpAddress();
    }

    @Override
    public Object getDetails() {
        if (!isAuthenticated()) return null;
        return sessionUser;
    }

    @Override
    public Object getPrincipal() {
        if (!isAuthenticated()) return null;
        return sessionUser.getSessionId();
    }

    @Override
    public boolean isAuthenticated() {
        return sessionUser != null;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        if (!isAuthenticated()) return null;
        return sessionUser.getName();
    }
}
