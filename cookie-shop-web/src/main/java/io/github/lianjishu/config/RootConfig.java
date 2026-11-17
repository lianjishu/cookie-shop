package io.github.lianjishu.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.lianjishu.properties.DataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 根容器：数据源、AOP、JdbcTemplate、事务、Service/DAO 扫描
 */
@Configuration
@EnableTransactionManagement //开启事务管理
@PropertySource("classpath:jdbc.properties")
@MapperScan("io.github.lianjishu.mapper")
@ComponentScan(basePackages = {
        "io.github.lianjishu.service",
        "io.github.lianjishu.interceptor",
        "io.github.lianjishu.properties",
})
public class RootConfig {

    private final DataSourceProperties dataSourceProperties;

    @Autowired
    public RootConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * Druid 数据源
     */
    @Bean
    DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());

        return dataSource;
    }

    /**
     * 配置 SqlSessionFactory：把数据源交给 MyBatis
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 数据源
        factoryBean.setDataSource(dataSource);
        // 设置实体类别名包
        factoryBean.setTypeAliasesPackage("io.github.lianjishu.entity");
        // Mapper 映射文件路径
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        "classpath*:io/github/lianjishu/mapper/**/*.xml"
                )
        );
        return factoryBean.getObject();
    }


    /**
     * 注入数据源并创建事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
