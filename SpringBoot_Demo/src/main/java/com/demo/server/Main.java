package com.demo.server;
import com.common.filter.PagingInterceptor;
import com.common.filter.XSSFilter;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @title : 启动类
 * @describle :
 * <p>
 * Create By tiger
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.demo.*,com.common.util")
@ComponentScan(basePackageClasses = XSSFilter.class)
@MapperScan("com.demo.mapper")
@PropertySource(value = {"classpath:config.properties"},encoding = "utf-8")
@Log4j
public class Main {

    public static void main(String[] args) {
        log.debug("启动中...");
        SpringApplication.run(Main.class, args);
        log.debug("启动完成!");
    }

    //mvc控制器
    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
        //增加拦截器
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new PagingInterceptor())    //指定拦截器类
                    .addPathPatterns("/**/page*").addPathPatterns("/**/*Page").addPathPatterns("/**/*Page*"); //指定该类拦截的url
            //如果有继续添加
        }
    }
}