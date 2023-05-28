package config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DBconfig {
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();	//Connection Pool 객체
		try {
			ds.setDriverClass("org.mariadb.jdbc.Driver");

			//192.168.0.113

			ds.setJdbcUrl("jdbc:mariadb://192.168.0.113:3306/savedogs");
			ds.setUser("scott");
			ds.setPassword("1234");
			ds.setMaxPoolSize(20);		//최대 연결 객체의 개수
			ds.setMinPoolSize(3);		//최소 연결 객체의 개수
			ds.setInitialPoolSize(5);	//초기의 연결 객체의 개수
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {	//mybatis 설정
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}