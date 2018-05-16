package com.jinhui.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 交易平台数据源配置
 *
 * @autor wsc
 * @create 2018-02-08 9:30
 **/
@Configuration
@MapperScan(basePackages = "com.jinhui.*.mapper", sqlSessionTemplateRef = "coinSqlSessionTemplate")
public class CoinDataSourceConfig {
    @Bean(name = "coinDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.coin")
    @Primary
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "coinTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("coinDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "coinSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("coinDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        /**
         * classpath* 表示扫描jar包中的文件,在多模块项目中，才能扫到全部的映射文件
         */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml"));
        return bean.getObject();
    }

    @Bean(name = "coinSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("coinSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 使用事务要指定@Transactional(value = "coinTransactionManager")
     */
    @Bean
    public PlatformTransactionManager coinTransactionManager(@Qualifier("coinDataSource") DataSource coinDataSource) {
        return new DataSourceTransactionManager(coinDataSource);
    }
}
