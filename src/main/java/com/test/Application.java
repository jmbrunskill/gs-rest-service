package com.test;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author weberli
 *
 */
@ComponentScan
@Configuration
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	transactionManager.setDataSource(dataSource());
	return transactionManager;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
	driverManagerDataSource.setUsername("test");
	driverManagerDataSource.setPassword("test");

	return driverManagerDataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());

	return jdbcTemplate;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
