package com.demo.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工具配置类
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    @Value("${spring.threadPool.corePoolSize}")
    private int corePoolSize = 10;//线程池维护线程的最少数量
    @Value("${spring.threadPool.maxPoolSize}")
    private int maxPoolSize = 30;//线程池维护线程的最大数量
    @Value("${spring.threadPool.queueCapacity}")
    private int queueCapacity = 8; //缓存队列
    @Value("${spring.threadPool.keepAlive}")
    private int keepAlive = 60;//允许的空闲时间
    @Value("${spring.threadPool.allowCoreThreadTimeOut}")
    private boolean allowCoreThreadTimeOut = false;//核心线程是否需要空闲时间
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(keepAlive);
        //核心线程是否需要空闲时间
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        // 设置默认线程名称
        executor.setThreadNamePrefix("mqExecutor-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.initialize();

        return executor;
    }
}
