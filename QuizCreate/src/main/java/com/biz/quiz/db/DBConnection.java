package com.biz.quiz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBContract에 입력한 데이터들을 가지고 DB와 연결하는 클래스.
public class DBConnection {

	
private static Connection dbConn ;
	
	static {
		
		try {

			Class.forName(DBContract.ORACLE_PRO.Driver);
			dbConn = DriverManager.getConnection(DBContract.ORACLE_PRO.url,
											DBContract.ORACLE_PRO.user,
											DBContract.ORACLE_PRO.password);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
