package com.biz.quiz.db;

// DB에 연결하기위한 정보들을 기입하는 클래스
public class DBContract {

	public static class ORACLE_PRO {
		public static final String Driver = "oracle.jdbc.driver.OracleDriver" ;
		public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		public static final String user = "myQuiz";
		public static final String password = "1234";	
	}
}
