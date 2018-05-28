package cn.pomelo.web.config;

import cn.pomelo.web.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * filter config
 *
 * @author: zhengyong Date: 2018/5/28 Time: 下午1:11
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean sessionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setName("sessionFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * auth filter
     *
     * @return
     */
    @Bean(name = "sessionFilter")
    public Filter sessionFilter() {
        return new SessionFilter();
    }
}
