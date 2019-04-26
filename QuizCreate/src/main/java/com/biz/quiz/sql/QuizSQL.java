package com.biz.quiz.sql;

// CRUD SQL을 작성해두고 ibatis가 가져가는 클래스
public class QuizSQL {

	public static final String CBT_ALL = 
			" SELECT * FROM tbl_cbt ";
	public static final String CBT_FIND_NUM = 
			" SELECT * FROM tbl_cbt WHERE cb_num = #{cb_num} " ;
	public static final String CBT_INSERT = 
			" INSERT INTO tbl_cbt (cb_num, cb_problem, cb_answer, "
		    + " cb_nanswer1, cb_nanswer2, cb_nanswer3, cb_nanswer4 ) "
			+ " VALUES (seq_cbt.nextval, #{cb_problem}, #{cb_answer}, "
			+ " #{cb_nanswer1}, #{cb_nanswer2}, #{cb_nanswer3}, #{cb_nanswer4} )";
	
	
	public static final String CBT_UPDATE = 
			" UPDATE tbl_cbt SET "
			+ " cb_problem = #{cb_problem}, cb_answer = #{cb_answer}, "
			+ " cb_nanswer1 = #{cb_nanswer1}, cb_nanswer2 = #{cb_nanswer2}, "
			+ " cb_nanswer3 = #{cb_nanswer3}, cb_nanswer4 = #{cb_nanswer4} "
			+ " WHERE cb_num = #{cb_num} " ;
	public static final String CBT_DELETE = 
			" DELETE FROM tbl_cbt WHERE cb_num = #{cb_num} " ;
}
