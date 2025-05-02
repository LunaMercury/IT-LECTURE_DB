package com.example.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
	@Autowired
	private DataSource dataSource3;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3); // bean에서 dataSource3를 hikari 로 설정했으므로 hikari 방식이 사용될 예정

		// Mapper XML 파일의 위치 설정
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
		sessionFactory.setMapperLocations(resources);

		return sessionFactory.getObject();
	}

	// -----------------------
	// sqlSession 생성
	// -----------------------
	// 위의 sqlSessionFactory() 를 그대로 쓰는 방법도 있다. 상세한 방법은 못들음
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}