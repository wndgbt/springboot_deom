package com.demo.config;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
public class MyBatisPageHelperConfig implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource;
    @Value("${mybatis.mapper-locations}")
    private String mybatisMapperLocation;
    @Value("${mybatis.type-aliases-package}")
    private String mybatisTypeAliasesPackage;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        //总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
        properties.setProperty("returnPageInfo", "check");
        //设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setMapperLocations(resolver.getResources(mybatisMapperLocation));
            sessionFactory.setTypeAliasesPackage(mybatisTypeAliasesPackage);
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
