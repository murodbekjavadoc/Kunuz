package main.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
    @Autowired
    private TokenFilter tokenFilter;
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);

        bean.addUrlPatterns("/region/adm");
        bean.addUrlPatterns("/region/adm/*");

        bean.addUrlPatterns("/category/adm");
        bean.addUrlPatterns("/category/adm/*");

        bean.addUrlPatterns("/profile/adm");
        bean.addUrlPatterns("/profile/adm/*");

        bean.addUrlPatterns("/articleType/adm");
        bean.addUrlPatterns("/articleType/adm/*");

        return bean;
    }
}
