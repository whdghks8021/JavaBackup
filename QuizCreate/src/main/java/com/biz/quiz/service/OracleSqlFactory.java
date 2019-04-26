package com.biz.quiz.service;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.biz.quiz.dao.QuizDao;
import com.biz.quiz.db.DBContract;
import com.biz.quiz.db.QuizDataSourceFactory;

// DB와 연결하여 준비하는 클래스
public class OracleSqlFactory {

	SqlSessionFactory sessionFactory ;
	
	public SqlSessionFactory getSessionFactory() {
		return this.sessionFactory ;
	}
	public OracleSqlFactory() {
		Properties props = new Properties();
		
		props.put("DRIVER", DBContract.ORACLE_PRO.Driver);
		props.put("URL", DBContract.ORACLE_PRO.url);
		props.put("USER", DBContract.ORACLE_PRO.user);
		props.put("PASSWORD", DBContract.ORACLE_PRO.password);
		
		DataSourceFactory dataSourceFactory = new QuizDataSourceFactory();
		dataSourceFactory.setProperties(props);
		
		DataSource dataSource = dataSourceFactory.getDataSource();
				
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment environment = new Environment("QuizEnv",transactionFactory, dataSource);
		
		Configuration config = new Configuration(environment);
		config.addMapper(QuizDao.class);
		
		sessionFactory = new SqlSessionFactoryBuilder().build(config);	
	}
}
