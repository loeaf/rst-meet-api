package com.loeaf.config;

import com.loeaf.common.conn.BoardConnMapper;
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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * board db 접속관련 for mybatis
 */
@Slf4j
@Configuration
@MapperScan(value="com.loeaf", annotationClass = BoardConnMapper.class, sqlSessionFactoryRef = "boardSqlSessionFactory")
@EnableTransactionManagement
public class BoardDatabaseConfig {

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("lhdt.svc");
//		factory.setDataSource(analsDataSource());
//		return factory;
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//	    JpaTransactionManager transactionManager = new JpaTransactionManager();
//	    transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
//
//	    return transactionManager;
//	}

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }


    /**
     * TODO  각  접속정보 암호화 필요
     * @return
     */
//	@Bean(name="boardDataSource")
//	@Primary
//	@ConfigurationProperties(prefix="app.datasource.anals")
//	public DataSource boardDataSource() {
//		DriverManagerDataSource dmds = DataSourceBuilder.create().type(DriverManagerDataSource.class).build();
//
//		//
//		log.info("<< {}", ToStringBuilder.reflectionToString(dmds));
//		return dmds;
//	}

    @Bean(name = "boardSqlSessionFactory")
    @Primary
    public SqlSessionFactory boardSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext context) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mybatis/**/*.xml"));
//        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis-config.xml"));


        //
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        //
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
