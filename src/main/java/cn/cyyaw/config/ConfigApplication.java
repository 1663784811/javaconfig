package cn.cyyaw.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }


    // 第一个Filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(CorsFilter corsFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(corsFilter);
        filterRegistrationBean.setOrder(0);
        filterRegistrationBean.setName("解决跨域问题");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
        source.setAlwaysUseFullPath(true);
        return new CorsFilter(source);
    }


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 允许任何头
        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

}
