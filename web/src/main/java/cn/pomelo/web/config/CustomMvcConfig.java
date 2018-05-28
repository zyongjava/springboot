package cn.pomelo.web.config;

import cn.pomelo.web.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static cn.pomelo.web.constant.Constants.ALLOWED_PATHS;

/**
 * MVC配置
 *
 * @author: zhengyong Date: 2018/5/28 Time: 上午10:02
 */
@Configuration
public class CustomMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(customInterceptor()).addPathPatterns("/**").excludePathPatterns(ALLOWED_PATHS.toArray(new String[ALLOWED_PATHS.size()]));
    }

    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
}
