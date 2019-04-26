package com.biz.oracle.service;

import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.biz.oracle.dao.ScoreDao;
import com.biz.oracle.dao.StdDao;
import com.biz.oracle.db.DBContract;
import com.biz.oracle.db.StdDataSourceFactory;

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
		
		DataSourceFactory dataSourceFactory = new StdDataSourceFactory();
		dataSourceFactory.setProperties(props);
		
		DataSource dataSource = dataSourceFactory.getDataSource();
				
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment environment = new Environment("GradeEnv",transactionFactory, dataSource);
		
		Configuration config = new Configuration(environment);
		config.addMapper(StdDao.class);
		config.addMapper(ScoreDao.class);
		
		sessionFactory = new SqlSessionFactoryBuilder().build(config);	
	}
}
