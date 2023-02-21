package com.loeaf.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * board db 접속관련 for mybatis
 */
@Slf4j
@Configuration
@MapperScan(value="com.loeaf.*.mapper", sqlSessionFactoryRef = "boardSqlSessionFactory")
@EnableTransactionManagement
public class BoardDatabaseConfig {
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "boardSqlSessionFactory")
    @Primary
    public SqlSessionFactory boardSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext context) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mybatis/**/*.xml"));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        log.info("<< {}", ToStringBuilder.reflectionToString(sqlSessionFactory));
        return sqlSessionFactory;
    }

    @Bean(name = "analsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate analsSqlSessionTemplate(SqlSessionFactory boardSqlSessionFactory) throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(boardSqlSessionFactory);

        //
        log.info("<< {}", ToStringBuilder.reflectionToString(sqlSessionTemplate));
        return sqlSessionTemplate;
    }
}
